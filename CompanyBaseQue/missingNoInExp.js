const s = "3+(2*(3*_-8)) = -1"
const exp = s.substr(0,s.indexOf(" ="))
const val = s.substr(s.indexOf("= ")+2, s.length-1)
for(let i = 0; i < 10; i++) {
  if(eval(exp.replace("_",i)) == val) {
    console.log(i)
    break
  }
}