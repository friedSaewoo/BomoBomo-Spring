export function getSitterListByPage(data, callback){
    $.ajax({
        url: '/sitters/sitterFind', //Controller에서 요청 받을 주소
        type: 'get', //POST 방식으로 전달
        data: data,
        success: function (result) { //컨트롤러에서 넘어온 값을 받는다
            if(callback){
                callback(result);
            }
        },
        error: function (a,b,c){
            console.error(c);
        }
    });
}

export function getSitterListAddrPage(data, callback){
    $.ajax({
        url: '/sitters/sitterAddrList', //Controller에서 요청 받을 주소
        type: 'get', //POST 방식으로 전달
        data: data,
        success: function (result) { //컨트롤러에서 넘어온 값을 받는다
            console.log("테스트 : " + result);
            if(callback){
                callback(result);
            }
        },
        error: function (a,b,c){
            console.error(c);
        }
    });
}