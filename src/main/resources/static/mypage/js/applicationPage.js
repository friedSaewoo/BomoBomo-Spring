//버튼 체크시 성별 단락이 나타나는 코드



$(document).ready(function() {

    $('.one-check').change(function(){
        if($('.one-check').is(':checked')){
            console.log($('.one-check').is(':checked'));
            $('.text-area-1').hide();
        }
    })

    $('.two-check').change(function(){
        if($('.two-check').is(':checked')){
            console.log($('.two-check').is(':checked'));
            $('.text-area-1').show();
        }
    })

});


//남자, 여자 선택시 색상 변화
$(document).ready(function(){
    let $genderbox = $('.gender-box');
// console.log("안ㄴ여!");
    $genderbox.on('click',function(e){
        let idx=$genderbox.index(this);
        console.log("안ㄴ여!");
        for(let i=0;i<$genderbox.length; i++){

            if(i == idx){
                $genderbox.eq(i).addClass('checked');

            }else{
                $genderbox.eq(i).removeClass('checked');
            }
        }

    })
});

$(document).ready(function(){
    let $genderboxsecond = $('.gender-box-second');
// console.log("안ㄴ여!");
    $genderboxsecond.on('click',function(e){
        let idx=$genderboxsecond.index(this);
        // function의 매개변수로 들어오는 값에 대해 묻기
        console.log(idx);
        console.log("두번쨰 안녕!!");
        for(let i=0;i<$genderboxsecond.length; i++){

            if(i == idx){
                $genderboxsecond.eq(i).addClass('checked-second');

            }else{
                $genderboxsecond.eq(i).removeClass('checked-second');
            }
        }

    })
});

//저장하기 클릭시 색 변화와 클릭이후 페이지 이동 코드
$(document).ready(function(){
    $('.btn').on('mouseover',function(){
        $(this).css("backgroundColor","#FF7000");
        $(this).css("color","white");
        $(this).css("fontWeight","bold");
        console.log("asd")
    })


    $('.btn').on('mouseout',function(){
        $(this).css("backgroundColor","white");
        $(this).css("color","#FF7000");
        console.log("asd")
    })
/*성별 미선택시 선택을 하도록 처리하는 코드*/
    $('.btn').on('click',function(){
        console.log($(".one-check").is(":checked"));
        let $gender=$('input[name=genderFirst]')
        let $genderSecond =$('input[name=genderSecond]')
        console.log($gender);
        console.log($genderSecond);

        if(!$gender.eq(0).is(':checked')&&!$gender.eq(1).is(':checked')){
            alert("성별을 선택하세요!");
            return;

        }

        if($(".two-check").is(':checked')){
            if(!$genderSecond.eq(0).is(':checked') && !$genderSecond.eq(1).is(':checked')){
                console.log('hi');
                alert("성별을 선택하세요!");
                return;
            }
        }
          alert("저장이 완료되었습니다.");
         $('.check-area').submit();
    })
});
