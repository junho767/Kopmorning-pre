document.addEventListener('DOMContentLoaded', function() {
    const nickname = document.getElementById('nickname');
    const year = document.getElementById('year');
    const reason = document.getElementById('reason');
    const cancelBtn = document.getElementById('cancelBtn');
    const completeBtn = document.getElementById('completeBtn');
    const inputNickname = document.getElementById('inputNickname');
    const inputYear = document.getElementById('inputYear');
    const inputReason = document.getElementById('inputReason');
    // 수정하기 버튼에 이벤트 리스너 추가
    document.querySelector('.modify-btn').addEventListener('click', function() {

        nickname.style.display = 'none';
        year.style.display = 'none';
        reason.style.display = 'none';

        // input 태그들을 보여줌
        inputNickname.style.display = 'block';
        inputNickname.style.border = '1px solid #ccc';
        inputNickname.style.borderRadius = '20px';
        inputNickname.style.padding = '5px';
        inputNickname.style.width = '250px';

        inputYear.style.display = 'block';
        inputYear.style.border = '1px solid #ccc';
        inputYear.style.borderRadius = '20px';
        inputYear.style.padding = '5px';
        inputYear.style.width = '250px';

        inputReason.style.display = 'block';
        inputReason.style.border = '1px solid #ccc';
        inputReason.style.borderRadius = '20px';
        inputReason.style.padding = '5px';
        inputReason.style.width = '250px';

        // 버튼을 보여줌
        completeBtn.style.display = 'block';
        cancelBtn.style.display = 'block';
    });
    cancelBtn.addEventListener('click', function() {

        nickname.style.display = 'block';
        year.style.display = 'block';
        reason.style.display = 'block';

        // input 태그들을 보여줌
        inputNickname.style.display = 'none';
        inputYear.style.display = 'none';
        inputReason.style.display = 'none';
        completeBtn.style.display = 'none';
        cancelBtn.style.display = 'none';
    });

    completeBtn.addEventListener('click', function(){
        let Nickname = document.getElementById('inputNickname').value;
        let Year = document.getElementById('inputYear').value;
        let Reason = document.getElementById('inputReason').value;
        body = JSON.stringify({
            nickname : Nickname,
            year : Year,
            reason : Reason
        });
        function success(){
            alert("성공!");
            location.reload();
        }
        function fail(){
            alert("실패!");
            location.reload();
        }
        httpRequest('POST', '/api/myProFil', body, success ,fail);
    });
});
function httpRequest(method, url, body, success, fail) {
    fetch(url, {
        method: method,
        headers: { // 로컬 스토리지에서 액세스 토큰 값을 가져와 헤더에 추가
            Authorization: 'Bearer ' + localStorage.getItem('access_token'),
            'Content-Type': 'application/json',
        },
        body: body,
    }).then(response => {
        if (response.status === 200 || response.status === 201) {
            return success();
        }
        else {
            alert(response.status);
            return fail();
        }
    });
}