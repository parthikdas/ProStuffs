const data = [
    { name: "Parthik", age: 21},
    { name: "Apple", age: 24},
    { name: "Parthik", age: 22},
]

const newData = []
const set = new Set()

for (person of data) {
    if(!set.has(person.name)) {
        set.add(person.name)
        newData.push(person)
    }
}
// [{name: "Parthik", age: 21}, {name: "Apple", age: 24}]