document.addEventListener("DOMContentLoaded", function() {
    const slider = document.querySelector('.slides');
    let currentIndex = 0;
    const slideCount = slider.children.length;
    const slideWidth = 1448; // 슬라이더의 너비를 맞춰주세요.
    const slideInterval = 3000; // 3초 간격으로 설정

    setInterval(function() {
        currentIndex = (currentIndex + 1) % slideCount;
        slider.style.transform = `translateX(-${currentIndex * slideWidth}px)`;
    }, slideInterval);
});