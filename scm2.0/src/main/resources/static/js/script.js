console.log("From script file")

let queryString= window.location.href;
const arr=queryString.split('/');
queryString=arr[arr.length-1];
console.log(queryString);


let currentTheme=getTheme();
document.addEventListener('DOMContentLoaded',()=>{
    changeTheme();
})


function changeTheme(){
    console.log(currentTheme)
    changePageTheme(currentTheme,currentTheme);
    
    let changeThemeBtn=document.querySelector('#changethemebtn');
   
    changeThemeBtn.addEventListener("click",(event)=>{
        let oldTheme=currentTheme;
        if(currentTheme==="dark"){
            currentTheme="light";
            
        }else{
            currentTheme="dark";
            changeThemeBtn.querySelector("span").textContent="Light"
        }
        changePageTheme(currentTheme,oldTheme);
    });
    
}
function changePageTheme(theme,oldTheme){
        setTheme(theme);
        document.querySelector("html").classList.remove(oldTheme);
        document.querySelector("html").classList.add(theme);
        document.querySelector('#changethemebtn')
        .querySelector("span").textContent=(theme==="light")?"Dark":"Light";
}
function setTheme(inputTheme){
    localStorage.setItem("theme",inputTheme);
}
function getTheme(){
    let theme=localStorage.getItem("theme");
    return theme?theme:"light";
}