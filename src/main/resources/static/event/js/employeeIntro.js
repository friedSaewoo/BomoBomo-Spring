// 데이터를 불러온 후 data 변수에 저장한다고 가정합니다.
let data = [
    { eventName: "Event 1" },
    { eventName: "Event 2" },
    { eventName: "Event 3" },
    // ... 더 많은 데이터 항목
];

let $slideContainer = $('.slide-container');
let slideWidth = 1051;
let currentIdx = 0;
let itemsPerSlide = 5;
let slideCnt = Math.ceil(data.length / itemsPerSlide);

// 데이터를 슬라이드로 분할하여 화면에 추가하는 함수
function renderSlides() {
    $slideContainer.empty(); // 이전에 표시된 슬라이드를 지웁니다.

    for (let i = currentIdx * itemsPerSlide; i < (currentIdx + 1) * itemsPerSlide && i < data.length; i++) {
        let eventData = data[i];
        let $slide = $('<div class="slide"></div>');
        $slide.append(`<img src="/event/img/event2.jpg" alt="소개사진1"/>`);
        $slide.append(`<p>${eventData.eventName}</p>`);
        $slideContainer.append($slide);
    }
}

function textLengthOverCut(txt, len, lastTxt) {
    if (len == "" || len == null) { // 기본값
        len = 20;
    }
    if (lastTxt == "" || lastTxt == null) { // 기본값
        lastTxt = "...";
    }
    if (txt.length > len) {
        txt = txt.substr(0, len) + lastTxt;
    }
    return txt;
}

renderSlides(); // 페이지 로드시 초기 슬라이드 표시

$('.recently-right-button').stop().on('click', function () {
    if (currentIdx < slideCnt - 1) {
        currentIdx++;
        let slideOffset = currentIdx * itemsPerSlide * slideWidth;
        $slideContainer.css('left', -slideOffset);
        $slideContainer.css('transition', '0.5s ease');
        renderSlides();
    }
});

$('.recently-left-button').on('click', function () {
    if (currentIdx > 0) {
        currentIdx--;
        let slideOffset = currentIdx * itemsPerSlide * slideWidth;
        $slideContainer.css('left', -slideOffset);
        $slideContainer.css('transition', '0.5s ease');
        renderSlides();
    }
});