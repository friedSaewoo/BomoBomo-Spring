$(function () {
    $(".sitterSelBtn").click(function () {

        $(".modal").fadeIn();
        $(".container").css("pointer-events", "none")
        $("body").css("background-color", "lightgray")
    });

    $(".modalX").click(function () {
        $(".modal").fadeOut();
        $(".container").css("pointer-events", "auto")
        $("body").css("background-color", "white")
    });

});


function openModal(modalname) {
    $("#modal").fadeIn(300);
    $("." + modalname).fadeIn(300);
    $('.sitterInformation').css("pointer-events", "none")

}

$("#modal, .close").on('click', function () {
    $("#modal").fadeOut(300);
    $(".modal-con").fadeOut(300);
    $('.sitterInformation').css("pointer-events", "auto")
});


$('.mon').click(function () {

    if ($(this).hasClass("mon") === true) {
        $(this).addClass("day");
        $(this).removeClass("mon");
        return false;
    } else {
        $(this).addClass("mon");
        $(this).removeClass("day");

    }

});

$(".sitterTime").click(function () {

    if ($(this).hasClass("sitterTime") == true) {
        $(this).addClass("timeSitter")
        $(this).removeClass("sitterTime")
    } else {
        $(this).addClass("sitterTime")
        $(this).removeClass("timeSitter")

    }

});

// function sitterInfo() {

// $('.sitterIndividual').click(function () {
//
//     let empNumber = $('.empNumber').val();
//     let sitterName = $('.sitterName').text();
//     alert("이름 : " + sitterName);
//     alert("번호 : " + empNumber);
//
// });

// }


// select 주소 설정
// $('document').ready(function () {
    var area0 = ["주소 선택", "서울특별시", "인천광역시"]
    var area1 = ["강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"];
    var area2 = ["계양구", "남구", "남동구", "동구", "부평구", "서구", "연수구", "중구", "강화군", "옹진군"];

    // 시/도 선택 박스 초기화

    $("select[name^=sido]").each(function () {
        $selsido = $(this);
        $.each(eval(area0), function () {
            $selsido.append("<option id='city' value='" + this + "'>" + this + "</option>");
        });
        $selsido.next().append("<option id='city' value=''>상세 선택</option>");
    });

    // 시/도 선택시 구/군 설정

    $("select[name^=sido]").change(function () {
        var area = "area" + $("option", $(this)).index($("option:selected", $(this))); // 선택지역의 구군 Array
        var $gugun = $(this).next(); // 선택영역 군구 객체
        $("option", $gugun).remove(); // 구군 초기화

        if (area == "area0")
            $gugun.append("<option value=''>상세 선택</option>");
        else {
            $.each(eval(area), function () {
                $gugun.append("<option id='cityAddr' value='" + this + "'>" + this + "</option>");
            });
        }
    });

    // $('.sitterTerBtn').click(function () {
    function sitterTerBtn(){

        let city = $("select[name=sido1] option:selected").text();
        let cityAddr = $("select[name=gugun1] option:selected").text();

        alert("시,도는 : " + city + ", 구,군은 : " + cityAddr);

        $.ajax({
            url: '/sitter/addrCheck', //Controller에서 요청 받을 주소
            type: 'post', //POST 방식으로 전달
            data: {city: city, cityAddr: cityAddr},
            // success: function (cityNum) { //컨트롤러에서 넘어온 cnt값을 받는다
            // },
            error: function () {
                alert("에러입니다");

            }

        });
        $('.sitterAddr').submit();
        $("#modal").fadeOut(300);
        $(".modal-con").fadeOut(300);
        $('.sitterInformation').css("pointer-events", "auto")


    // });
    };
// });


// $('.sitterTerBtn').click(function () {
//
//     if($("div").hasClass("day") === true) {
//         let day = $('.day').text();
//
//         let dayL = $('.day').length;
//         alert("날짜!!! : " + day);
//         console.log(day);
//
//         alert("날짜 수 : " + dayL);
//     } else {
//         alert("날짜 선택 없음")
//     }
//
//     if($("div").hasClass("timeSitter") === true) {
//       let time = $('.timeSitter').text();
//       alert("선택한 시간 : " + time);
//     } else {
//         alert("시간 선택 없음")
//     }
// });

//select 주소 설정



