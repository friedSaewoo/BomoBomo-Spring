// 마이페이지 시터 리뷰 비동기터리 모듈 import
import * as myPage from './module/myPageModule.js';
import {getEventReviewList} from "./module/myPageModule.js";

// 후기관리 창 띄우기
var isBoardVisible = false; // 초기에는 보이지 않는 상태
var isNull=false;
$('.reviewtab1').on('click', function() {
    if (!isNull) {
        // 다른 요소 숨김
        $('.review-list-area1').css('display', 'none');
        // 현재 요소 스타일 변경
        $('.reviewtab2').css('backgroundColor', 'white');
        $('.reviewtab2').css('color', 'black');
    }
    if (isBoardVisible) {
        // 이미 보이는 상태이면 숨김
        $('.reviewtab1').css('backgroundColor', 'white');
        $('.reviewtab1').css('color', 'black');
        $('.review-list-area').css('display', 'none');
    } else {
        $('.reviewtab1').css('backgroundColor', '#FF7000');
        $('.reviewtab1').css('color', 'white');
        $('.review-list-area').css('display', 'block'); // 보이지 않는 상태이면 보이게 함
    }

    // isBoardVisible = !isBoardVisible;
    isNull=false;// 상태를 반대로 변경
    console.log(1);
});

// 이벤트관리 창 띄우기

$('.reviewtab2').on('click',function(){
    if (!isBoardVisible) {
        // 다른 요소 숨김
        $('.review-list-area').css('display', 'none');
        // 현재 요소 스타일 변경
        $('.reviewtab1').css('backgroundColor', 'white');
        $('.reviewtab1').css('color','black');
        console.log("색상바꾸기 성공!")
    }
    if(isNull){
        $('.reviewtab2').css('backgroundColor', 'white');
        $('.reviewtab2').css('color', 'black');
        $('.review-list-area1').css('display', 'none');
    }else {
        $('.reviewtab2').css('backgroundColor', '#FF7000');
        $('.reviewtab2').css('color', 'white');
        $('.review-list-area1').css('display', 'block'); // 보이지 않는 상태이면 보이게 함
    }
    // isBoardVisible = false;
    // isNull =!isNull;
    isBoardVisible = false;

    console.log(2);
})

// 결제대기중 버튼 클릭이벤트
$('.siter-situation span').on('mouseover',function(){
    $(this).css('backgroundColor','#FF7000');
    $(this).css('color','white');
})

$('.siter-situation span').on('mouseout',function(){
    $(this).css('backgroundColor','white');
    $(this).css('color','#FF7000');
})

// 결제 페이지 클릭 버튼 이벤트
$('.btn').on('mouseover',function(){
    $(this).css('backgroundColor','#FF7000');
    $(this).css('color','white');
})

$('.btn').on('mouseout',function(){
    $(this).css('backgroundColor','white');
    $(this).css('color','#FF7000');
})

//결제 대기 클릭시 결제 페이지 작동

$('.siter-situation span').on('click',function(){
    let datastatus=$('.status').data('statusnum');
    // console.log(datastatus);
    if(datastatus == 0){
        console.log(datastatus);
        return;
    }
    $('.modal-pay').css('display','block');
    $('body').css('backgroundColor','rgba(0,0,0,0.5)');






})


//결제하기 결제하기 클릭시 카카오페이로 이동하는 js
$("#check_module").click(function () {
    var IMP = window.IMP; // 생략가능
    IMP.init('imp22267731');
    // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
    // ''안에 띄어쓰기 없이 가맹점 식별코드를 붙여넣어주세요. 안그러면 결제창이 안뜹니다.

    // DOM객체를 가져온다
        let estTitles = $('.estTitle').text();
        let amount = $('.totalMoney').text();
        let usernames =$('.user-name').text();
        let addressinfo = $('.address-info').text();
        let userNumber =$('.userinfo-userNumber').val();
       let matchNumber =$('.userinfo-matchNumber').val();

        let matchObj ={ userNumber : userNumber,
            matchNumber:matchNumber

        };
    IMP.request_pay({
        pg: 'kakaopay.TC0ONETIME',
        pay_method: 'card',
        merchant_uid: 'merchant_' + new Date().getTime(),
        /*
         *  merchant_uid에 경우
         *  https://docs.iamport.kr/implementation/payment
         *  위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
         */
        name: '서비스 이용료  ',
        // 결제창에서 보여질 이름
        // name: '주문명 : ${auction.a_title}',
        // 위와같이 model에 담은 정보를 넣어 쓸수도 있습니다.
        amount: amount,
        // amount: ${bid.b_bid},
        // 가격
        buyer_name: usernames,
        // 구매자 이름, 구매자 정보도 model값으로 바꿀 수 있습니다.
        // 구매자 정보에 여러가지도 있으므로, 자세한 내용은 맨 위 링크를 참고해주세요.
        buyer_postcode: addressinfo,
    }, function (rsp) {
        console.log(rsp);
        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            msg += '결제 금액 : ' + rsp.paid_amount;
            $.ajax({
                type: "patch",
                url: `/myPages/${matchNumber}`,
                data: JSON.stringify(matchObj),
                contentType : 'application/json; charset=utf-8'
            });

            window.location.href='/mypage/buy';
            // success.submit();
            // 결제 성공 시 정보를 넘겨줘야한다면 body에 form을 만든 뒤 위의 코드를 사용하는 방법이 있습니다.
            // 자세한 설명은 구글링으로 보시는게 좋습니다.
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            // 창이 사라지고 원래 페이지로 이동
            alert(msg);
            window.location.href='/mypage/main';
        }

    });
});


// 시터 리뷰를 작성한 내용을 메인에 비동기 뿌려주느 코드
let page = 1;

myPage.getSitterReviewList(page, showSitterReviewList);

function showSitterReviewList(result) {
    console.log(result);
    if (result.list.length == 0) {
        $('.table').css('display', 'none');
        console.log(result);
    } else {
        $('.table').css('display','block');
        let text = ``;

        text += `
        <tr class="review-text top">
            <td class="review-num">번호</td>
            <td class="review-title">제목</td>
            <td class="review-data">날짜</td>
        </tr>
    `;

        result.list.forEach(review => {
            text += `

            <tr class="review-text">
                <td class="review-num">${review.rnum}</td>
                <td class="review-title">
                    <a href="/board/reviewDetail?sitterBoardNumber=${review.sitterBoardNumber}">${review.sitterBoardContent}</a>
               
                </td>
                <td class="review-data">${review.sitterBoardRegisterDate}</td>
            </tr>
        `;
        });

        $('.table').html(text);


        let pageInfo = '';

        let pageVo = result.pageVo;
        if (pageVo.prev) {
            pageInfo += `
            
                <li class="page-num prev">
                    <a href="#"  data-page="${pageVo.startPage - 1}">&lt;</a>
                </li>
           
        `;
        }
        if (pageVo.realEnd != 0) {
            for (let i = pageVo.startPage; i <= pageVo.endPage; i++) {
                if (i == pageVo.criteria.page) {
                    pageInfo += `
                <li class="page-num active-num" >
                    <a href="#" data-page="${i}" >${i}</a>
                 </li>
            
        `;
                } else {
                    pageInfo += `
            
                <li class="page-num">
                    <a href="#" data-page="${i}">${i}</a>
                    </li>
        `;
                }
            }
        }

        if (pageVo.next) {
            pageInfo += `
            
                <li class="page-num next">
                    <a href="#"  data-page="${pageVo.endPage + 1}">&gt;</a>
                </li>
            
        `;
        }
        console.log(pageInfo)
        $('.page').html(pageInfo);
    }
}

$('.page').on('click', '.page-num a', function (e){
    e.preventDefault();
    page = $(this).data('page');
    myPage.getSitterReviewList(page, showSitterReviewList);
});





//이벤트 리뷰를 작성한 내용을 게시판으로 뿌려주는 코드

let pages =1;
myPage.getEventReviewList(pages,showEventReviewList);

function showEventReviewList(result){

    if(result.eventlist.length == 0){
        $('.table1').css('display','none');
        console.log(result);
    }else {
        $('.table1').css('display','block');
        let texts = ``;

        texts += ` <tr class="review-text1 top1">
                   <td class="review-num1">번호</td>
                   <td class="review-title1">제목</td>
                   <td class="review-data1">날짜</td>
              </tr>
            `;

        result.eventlist.forEach(eventreview => {
            texts += ` 
        
        <tr class="review-text1">
            <td class="review-num1">${eventreview.rnum}</td>
            <td class="review-title1">
                <a href="/board/reviewEventDetail?eventBoardNumber=${eventreview.eventBoardNumber}">${eventreview.eventBoardContent}</a>
            </td>
            <td class="review-data1">${eventreview.eventBoardRegisterDate}</td>
        </tr>
        `;
        });

        $('.table1').html(texts);

        let pagesInfo = '';

        let pagesVo = result.pageVO;


        if (pagesVo.prev) {
            pagesInfo += `
              <li class="page-num1 prev1" >
                <a href="#" class="page-a1" data-pages="${pagesVo.startPage - 1}">&lt</a>
              </li>
        `;
        }
        if (pagesVo.realEnd != 0) {
            for (let i = pagesVo.startPage; i <= pagesVo.endPage; i++) {
                if (i == pagesVo.criteria.page) {
                    pagesInfo += `
                <li class="page-num1 active-num1">
                    <a href="#" data-pages="${i}" >${i}</a>
                </li>
        `;
                } else {
                    pagesInfo += `
                <li class="page-num1" >
                    <a href="#" data-pages="${i}">${i}</a>
                </li>
        `;
                }
            }
        }

        if (pagesVo.next) {
            pagesInfo += `
     
                <li class="page-num1 next1" >
                    <a href="#" class="page-b1" data-pages="${pagesVo.endPage + 1}">&gt</a>
                </li>
         
        `;
        }
        console.log(pagesInfo)
        $('.page1').html(pagesInfo);
        console.log(pagesVo.prev);
        console.log(pagesVo.next);
    }
}

$('.page1').on('click', '.page-num1 a', function (e){
    e.preventDefault();
    pages = $(this).data('pages');
    console.log(pages);
    myPage.getEventReviewList(pages,showEventReviewList);
});



// 결제 정보를 출력
myPage.buyInfo(purchasePage);
function purchasePage(result){
    console.log("----------------");
    console.log(result);

    let text ='';

    let userInfos= result.userInfo;
        text = `<li class="user">
                                                    <p>주문자</p>
                                                    <input type="hidden" value="${userInfos.userNumber}" class="userinfo-userNumber">
                                                    <input type="hidden" value="${userInfos.matchNumber}" class="userinfo-matchNumber">
                                                    <p class="user-private-area">
                                                        <span class="user-name" >${userInfos.userName}</span>
                                                        <span class="user-phone">${userInfos.userPhone}</span>
                                                    </p>

                                                    <p class="user-email">${userInfos.userEmail}</p>
                                                </li>
                                                <li class="user-address">
                                                    <p>배송자 주소</p>
                                                    <p class="address-info">${userInfos.address}</p>
                                                    <p class="address-detail">${userInfos.addressDetail}</p>
                                                </li>
    
        `;

    $('.user-detail').html(text);


    let buyInfos =result.buyInfo;
    let texts = ``;
    buyInfos.forEach( buy => {

    texts += `    <li class="product-detail">
                                                    <p class="product-title">
                                                        <span>[보모보모]</span>
                                                        <span class="estTitle">${buy.estTitle}</span>
                                                    </p>
                                                    <p class="usetime">
                                                        <span>활동 :</span>
                                                        <span>${buy.estContent}</span>
                                                    </p>
                                                    <p class="pay">
                                                        <span>결제 금액</span>
                                                        <span class="money" data-price="${buy.estPrice}">${numberWithCommas(buy.estPrice)}원</span>
                                                    </p>
                   </li>
            
            
                `;


            $('.product-info').html(texts);


        })

    let prices = $('.money');
    let total=0;
    for (let i = 0; i < prices.length; i++) {

        let price = $(prices[i]).data("price");
        console.log(price);
        total += price;

    }
    console.log(total);
    let totalText=numberWithCommas(total);
    console.log(totalText);
    let texTs =``;
    texTs=`
              <p class="total-payment">
                                                <span>최종 결제 금액 : </span>
                                                <span class="totalMoney">${totalText}원</span>
                                            </p>
    
    `;

    $('.total-area').html(texTs);



}
$('.check-btn').on('click',  function (){
    myPage.buyInfo(purchasePage);

});
// 결제하기의 금액 ,마 함수 정리
function numberWithCommas(price) {
    console.log(price);
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

$('.page-remove').on('click',function (){
    $('.modal-pay').css('display','none');
    console.log("실행!!!");
    $('body').css('backgroundColor','white');
    console.log("실행!!!");
})



