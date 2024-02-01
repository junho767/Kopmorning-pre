function setBackgroundImage(){
    var element = document.getElementById('exampleElement');
    if(element){
        element.style.backgroundImage = "url('/img/logo.png')";
    }
}
window.onload = function () {
    setBackgroundImage();
};
