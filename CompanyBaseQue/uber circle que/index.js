const container = document.getElementById('container');
const coordinate = {} // { 0: [[x1, y1], [x2, y2]], 1: [[x1, y1], ...], ... }
const colors = {
    0: 'red',
    1: 'yellow',
    2: 'green',
    3: 'blue',
    4: 'cyan',
    5: 'lavender',
    6: 'pink',
    7: 'grey',
    8: 'orange'
};

const CIRCLE_SIZE = 100; // Diameter
const RADIUS = CIRCLE_SIZE / 2;

// Create a circle at the given position with the specified color and level
const createCircle = (x, y, color, level) => {
    const div = document.createElement('div');
    div.style.cssText = `
        height: ${CIRCLE_SIZE}px;
        width: ${CIRCLE_SIZE}px;
        position: absolute;
        left: ${x}px;
        top: ${y}px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 30px;
        background: ${color};
        border-radius: 50%; /* Make it circular */
    `;
    div.innerHTML = level;
    container.appendChild(div);
};

// Function to check if two circles overlap
const isOverlapping = (x1, y1, x2, y2, r1, r2) => {
    const distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    return distance <= (r1 + r2);
};

// Insert the new circle into the correct level and check for overlaps
const insertInObj = (relX, relY) => {
    let level = 0;

    // Check for overlaps with circles on current levels
    while (coordinate[level]) {
        const overlaps = coordinate[level].some(([x, y]) => isOverlapping(x, y, relX, relY, RADIUS, RADIUS));
        if (!overlaps) break; // No overlap, so place the circle here
        level++; // Move to next level
    }

    // Add the new circle's coordinates to the detected level
    if (!coordinate[level]) {
        coordinate[level] = [];
    }
    coordinate[level].push([relX, relY]);

    // Get the color from the color map based on the level (cycling through colors)
    const color = colors[level % Object.keys(colors).length] || 'black';
    return [color, level];
};

// Handle the click event to place the circle in the container
document.addEventListener('click', e => {
    const cont = container.getBoundingClientRect();
    const relX = e.clientX - cont.left;
    const relY = e.clientY - cont.top;

    // Get the color and level for the new circle
    const [color, level] = insertInObj(relX, relY);

    // Create the circle at the clicked position with the correct color and level
    createCircle(relX, relY, color, level);
});
