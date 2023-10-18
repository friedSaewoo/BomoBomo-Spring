$('.sub-menu').hide();


// 전체 슬라이드 다운

$(function () {
    let hh = $(".header-wrap").height();
    let ih = $(".sub-menu").innerHeight();
    let maxHeight = hh + ih;

    console.log(maxHeight)

    $(".subMenu").hide();

    $(".main-menu").mouseenter(function () {
        $(".header-container")
            .stop()
            .animate({ height: maxHeight }, 100, "linear", function () {
                $(".sub-menu").fadeIn();
            });
    });

    $(".header-container").mouseleave(function () {
        $(".header-container")
            .stop()
            .animate({ height: hh }, 200, "linear", function () {
                $(".sub-menu").hide();
            });
    });


});




$(window).scroll(function () {
    $(".header-container").css("left", 0 - $(this).scrollLeft());
});

