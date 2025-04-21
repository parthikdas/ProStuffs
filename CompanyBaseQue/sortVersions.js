const ip = [
    "1.10.2",
    "1.2.3",
    "v2.0.0",
    "3.0.1",
    "text",
    "2.0.10",
    "1.2.10",
    "v1.5.5",
    "1.2.2",
    "3.0.0",
    "random.string",
    "0.9.9"
  ];

console.log(ip.filter(e => !e.includes('v')).map(e => e.split(".")).sort((a,b) => a[0] == b[0] ? a[1] == b[1] ? a[2] - b[2] : a[1] - b[1] : a[0] - b[0]).map(e => e.join(".")))
// above is my code

const sorted = ip
  .filter(e => !e.includes('v') && /^\d+\.\d+\.\d+$/.test(e))  // Filter out 'v' and non-version strings
  .map(e => e.split(".").map(Number))                         // Split and convert to numbers
  .sort((a, b) =>                                             // Sort numerically
    a[0] - b[0] || a[1] - b[1] || a[2] - b[2]
  )
  .map(e => e.join("."));                                     // Convert back to version string

console.log(sorted);

// [
//     '0.9.9',
//     '1.2.2',
//     '1.2.3',
//     '1.2.10',
//     '1.10.2',
//     '2.0.10',
//     '3.0.0',
//     '3.0.1'
//   ]