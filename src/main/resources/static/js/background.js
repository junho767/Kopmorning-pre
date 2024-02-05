function setBackgroundImage(){
    var element = document.getElementById('exampleElement');
    if(element){
        element.style.backgroundImage = "url('/img/anfiled.png')";
    }
}
window.onload = function () {
    setBackgroundImage();
};
