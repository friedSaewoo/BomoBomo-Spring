import * as sitterModule from "./module/sitterFindModule.js";
import {getSitterListAddrPage} from "./module/sitterFindModule.js";

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

$('.modal-open').on('click', function (){
    openModal('modal1');
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


// select 주소 설정
// $('document').ready(function () {
var area0 = ["주소 선택", "서울특별시", "인천광역시"]
var area1 = ["강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"];
var area2 = ["계양구", "남구", "남동구", "동구", "부평구", "서구", "연수구", "중구", "강화군", "옹진군"];

// 시/도 선택 박스 초기화

$("select[name^=sido]").each(function () {
    let $selsido = $(this);
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



let page = 1;

sitterModule.getSitterListByPage({page: page}, showListAndPage);

//주소찾기 버튼 이벤트
$('.sitterTerBtn').on('click', function (){
    let city = $("select[name=sido1] option:selected").text();
    let cityAddr = $("select[name=gugun1] option:selected").text();

    if(city == "주소 선택") {
        Swal.fire({
            icon: 'error',
            title: '주소를 선택하세요.',
            text: '',
          });
        return;
    }
    page = 1;
    sitterModule.getSitterListAddrPage({page: page, city:city, cityAddr:cityAddr}, showListAndPage);
    $("#modal").fadeOut(300);
    $(".modal-con").fadeOut(300);
    $('.sitterInformation').css("pointer-events", "auto");

});


function showListAndPage(sitter) {
    let str = "";
    let city = $("select[name=sido1] option:selected").text();
    let cityAddr = $("select[name=gugun1] option:selected").text();

    $('.sitterInfoList').empty();
    $('.sitterFindCount > strong').empty();
    $('.paging').empty();

    console.log("sitter : " + sitter);
    $('.sitterFindCount > strong').append(sitter.sitterTotal);

    sitter.sitterList.forEach(function (sitterList) {
        str = "<div class='sitterIndividual'>" +
            "<a href='/sitter/sitterInfo?empNumber=" + sitterList.empNumber + "' class='sitterInformation'><div><div class='sitterInfoAndImg test'> <div class='sitterInfo'>";
        str += "<input type='hidden' value='" + sitterList.empNumber + "' class='empNumber' name='empNumber'> <div name='sitterName' class='sitterName'>";
        str += "<strong>" + sitterList.empName + "</strong>&nbsp;&nbsp;"
        str += "<span>" + sitterList.empGender + "</span></div>"
        if (sitterList.avg == null) {
            str += "<div class='sitterGrade'><img src='/common/img/star.png'> <span>" + 0.0 + "/ 5" + "</span></div>";
        } else {
            str += "<div class='sitterGrade'><img src='/common/img/star.png'> <span>" + sitterList.avg + "/ 5" + "</span></div>";
        }
        str += "<div>가능한 교육들</div></div>";
        str += "<div class='sitterImg'>"
        str += "<img  src='https://search.pstatic.net/common?type=b&amp;size=216&amp;expire=1&amp;refresh=true&amp;quality=100&amp;direct=true&amp;src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F202207%2F20220727155425320.jpg'></div></div></div>";

        str += "<div class='sitterContext'>"
        str += "<span>" + sitterList.empContent + "</span></div></a></div>";

        $('.sitterInfoList').append(str);
    });


    //=================페이징======================================
    let start = sitter.pageInfo.startPage;
    let end = sitter.pageInfo.endPage;

    let pageInfo = sitter.pageInfo;
    let pageStr = '';

    if (pageInfo.prev) {
        pageStr += `<li class='pageNum prev' data-pagenum='${start-1}'>&lt;</li>`;
    }

    for (let i = start; i <= end; i++) {
        if (pageInfo.criteria.page == i) {
            pageStr += `<li class='pageNum  active' data-pagenum='${i}'>${i}</li>`;
        } else {
            pageStr += `<li class='pageNum' data-pagenum='${i}'>${i}</li>`;
        }
    }

    if (pageInfo.next) {
        pageStr += `<li class='pageNum next' data-pagenum='${end+1}'>&gt;</li>`;
    }

    $('.paging').html(pageStr);
}


$('.paging').on('click', '.pageNum', function (){
    page = $(this).data('pagenum');
    sitterModule.getSitterListByPage({page: page}, showListAndPage);
});







