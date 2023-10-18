import * as reply from './module/boardReply.js';
import * as commonReply from './module/Reply.js';
import * as paging from './module/boardPagination.js';
import {getTextLength} from "./module/Reply.js";

//input hidden에 넣어놓은 sitterBoardNumber값 가져오기
let sitterBoardNumber = $('.reviewDetail-num').val();
console.log(sitterBoardNumber);

//댓글 첫화면 1페이지로 불러오기
let page = 1;
reply.getListServiceReviewPage({sitterBoardNumber,page:1}, showReply);



//페이징처리된 숫자 클릭 시 해당 데이터를 가져와서 비동기 페이징처리
$(document).on('click', '.page-num a', function (e) {
    e.preventDefault();
    const page = $(this).data('pagenum');
    reply.getListServiceReviewPage({sitterBoardNumber, page}, showReply);


    $('.page-num a').removeClass('active-page');
    $(this).addClass('active-page');

});


//댓글 등록
$('.btn-reply').on('click', function () {
    let content = $('#reply-content').val();

    if(!(content)){
        alert('내용을 입력해주세요');
        return;
    }

    if(commonReply.getTextLength(content)>200){
        alert("200자 이내로만 작성 가능합니다.");
        return;
    }

    let replyObj = {
        sitterCommentContent : content,
        sitterBoardNumber : sitterBoardNumber,
        userNumber : loginNumber
    };

    reply.addServiceReviewReply(replyObj, function(){
        reply.getListServiceReviewPage({sitterBoardNumber,page:1}, showReply);
    });
    
    //등록버튼 누르면 글자수 카운팅 초기화 및 입력내용도 초기화
    $('#reply-content').val('');
    $(".word-count").text('');
});




let listInfo = {

    sitterBoardNumber : sitterBoardNumber,
    page : 1

}



//댓글 수정 삭제버튼 팝업
$('.review-reply').on('click', '.reply-btns', function () {
    let $replyBtnBox = $(this).closest('.reply-btn-box').find('.reply-btns__box');
    $('.reply-btns__box').addClass('none');
    $replyBtnBox.toggleClass('none');
});


$('body').click(function (e) {
    if ($(e.target).hasClass('reply-btns')) {
        //console.log('aa');
        return;
    }
    if (!$('.reply-btns__box').has(e.target).length) {
        $('.reply-btns__box').addClass('none');
    }
});




//댓글 수정창 팝업
$('.review-reply').on('click', '.reply-modify-btn', function () {

    //리플 수정창 팝업 시 작성시간(일자) 숨기기
    let $hide = $('.reply-time');
    $hide.css('display','none')

    let $content = $(this).closest('.reply').find('.reply-box__content');
    $content.replaceWith(`
      <div class='modify-box'>
        <textarea class='modify-content' cols="30" rows="2">${$content.text()}</textarea>
        <button type='button' class='modify-content-btn'>수정 완료</button>
      </div>
      <div>
        <span class="textLengthCheck">
            ${commonReply.getTextLength($content.text()) + ' / 200'}
        </span>
      </div>
    `);
    $('.reply-btns__box').addClass('none');

});


// 리플 삭제 버튼 처리
$('.review-reply').on('click', '.reply-remove-btn', function () {
    $('.reply-btns__box').addClass('none');

    let sitterCommentNumber = $(this).closest('.reply').find('.reply-remove-btn').data('deletenum');
    reply.removeServiceReviewReply(sitterCommentNumber, function (){
        
        //댓글 삭제 시 페이징 1번으로 이동
        reply.getListServiceReviewPage({sitterBoardNumber,page:1}, showReply);
    });
});



// 리플 수정 완료 처리
    $('.review-reply').on('click', '.modify-content-btn', function () {

        console.log('modify!!!');
        let sitterCommentNumber = $(this).closest('.reply').find('.reply-modify-btn').data('modifynum');
        
        //해당 페이징에서 머무를 수 있도록 페이징값 가져오기
        let page = $('.active-page a').data('pagenum');
        let replyContent = $(this).closest('.modify-box').find('.modify-content').val();

        if(!replyContent){
            alert("내용을 입력해주세요")
            return;
        }
        
        if(commonReply.limitModifyText()>200){
            alert("200자 이내로 작성해주세요")
            return;
        }

        let replyObj = {
            sitterCommentContent : replyContent
        };

        reply.modifyServiceReviewReply(sitterCommentNumber, replyObj, function (){
            reply.getListServiceReviewPage({sitterBoardNumber,page:page}, showReply);
        });
    });



//댓글리스트
function showReply(result){
    let totalReply='';
    let text='';


    totalReply='댓글 '+result.totalReply +'개';

    result.replyList.forEach(r =>{

        text += `
            
     <dl>
        <dt>
            <div class="reply-box__writer" data-replyNum="${r.sitterCommentNumber}">
                <strong>${r.userId}</strong>
            </div>
        </dt>

        <dd>
            <div class="reply-list-wrap">
                <div class="reply">
                    <div class="reply-box">
                        <div class="reply-box__content"><p>${r.sitterCommentContent}</p></div>
                    </div>
                     <div class="reply-time">
                        ${commonReply.timeForToday(r.sitterCommentModifyDate) + (r.sitterCommentRegisterDate == r.sitterCommentModifyDate ? ' 작성' : ' 수정')}
                    </div>      
                     <div class="reply-btn-box">
                    `;

        if(r.userNumber == loginNumber){
            text+=  `
                        <span class="reply-btns"></span>
                        <div class="reply-btns__box none">
                            <div class="reply-remove-btn" data-deletenum="${r.sitterCommentNumber}">삭제</div>
                            <div class="reply-modify-btn" data-modifynum="${r.sitterCommentNumber}">수정</div>
                        </div>
                `;
        }

        text+=
            `          
              
                    </div>
                </div>
            </div>
        </dd>
     </dl>                         
            `;
    })

    //댓글이 한 개도 없으면 숨김
    if(result.totalReply!=0){
        $('.totalReply').css('display','block')

        $('.totalReply').html(totalReply);

    }else {
        $('.totalReply').css('display','none')
    }

    let $pagenation = $('.reply-pagenation-container ul');

    $('.review-reply').html(text);
    paging.updatePagination(result.pageReplyVo, $pagenation);

}



//댓글 입력창 및 수정창 글자 수 제한
commonReply.limitText();
commonReply.limitModifyText();

