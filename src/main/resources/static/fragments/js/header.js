$('.sub-menu').hide();


// 전체 슬라이드 다운

$(function () {
    let hh = $(".header-wrap").height();
    let ih = $(".sub-menu").innerHeight();
    let maxHeight = hh + ih;

    console.log(maxHeight)

    $(".subMenu").hide();

    $(".main-menu").mouseenter(function () {
        $(".header-contaier")
            .stop()
            .animate({ height: maxHeight }, 100, "linear", function () {
                $(".sub-menu").fadeIn();
            });
    });

    $(".header-contaier").mouseleave(function () {
        $(".header-contaier")
            .stop()
            .animate({ height: hh }, 300, "linear", function () {
                $(".sub-menu").hide();
            });
    });


});


//스크롤 다운 시 헤더숨기기
//스크롤 업 헤더 등장
// const navBar = $('header');
// let preScrollPros = window.scrollY;
//
// $(window).on('scroll', function (){
//     let currScrollPros = window.scrollY;
//
//     if(currScrollPros >= preScrollPros || currScrollPros==0){
//         navBar.css('display', 'block');
//     }else {
//         navBar.css('display', 'none');
//     }
//     preScrollPros = currScrollPros;
// })



$(window).scroll(function () {
    $(".header-contaier").css("left", 0 - $(this).scrollLeft());
});

