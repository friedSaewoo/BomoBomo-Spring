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

function empList(page, callback){
    $.ajax({
        url: `/events/empIntro/${page}`,
        type: 'get',
        dataType: 'JSON',
        success: function (result){
            if (callback){
                callback(result)
            }

            console.log(result.pageInfo)
            console.log(result.empIntro)
        }, error: function (a,b,c){
            console.error(c)
        }


    })
}
$(document).on('click', '.page-num a', function (e){
    e.preventDefault();
    const page = $(this).data('pagenum');
    empList(page,empViewList);



})

$(document).ready(function (){
    empList(1,empViewList)


})

function empViewList(result){

    let text = '';
    result.empIntro.forEach(r=>{
        text+=`
        <div class="board-item" >
    <div class="board-img-box">

        <img
            src = "/events/empImg?fileFullPath=${r.empImgUploadPath + '/' + r.empImgUuid + '_' + r.empImgName}">

    </div>
    <div class="board-item-text">
        <h3 class="board-item-title" >${r.empName}</h3>
        <div class="board-item-author" >${r.empContent}</div>
    </div>
</div>
        
        
       
        `;
    })
    $('.board').html(text)
    let pageing = $('.page-box')
    updatePagination(result.pageInfo,pageing)

}


function updatePagination(pageReviewVo, $pageSection) {
    $pageSection.empty();

    if (pageReviewVo.prev) {
        $pageSection.append(`
                <li class="page-num"><a href="#" data-pagenum="${pageReviewVo.startPage-1}">&lt;</a></li>
            `);
    }   for (let page = pageReviewVo.startPage; page <= pageReviewVo.endPage; page++) {
        if(page == pageReviewVo.criteria.page){
            $pageSection.append(`
                    <li class="page-num active-page"><a href="#" class="on" data-pagenum="${page}">${page}</a></li>
                `);

        }
        else{
            $pageSection.append(`
                    <li class="page-num"><a href="#" class="on" data-pagenum="${page}">${page}</a></li>
                `);
        }
    }    if (pageReviewVo.next) {
        $pageSection.append(`
            <li class="page-num"> <a href="#" data-pagenum="${pageReviewVo.endPage+1}">&gt;</a></li>
            `);
    }
}

