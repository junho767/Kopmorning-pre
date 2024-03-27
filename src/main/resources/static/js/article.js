// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        function success() {
            alert('삭제가 완료되었습니다.');
            location.replace('/articles');
        }

        function fail() {
            alert('삭제 실패했습니다.');
            location.replace('/articles');
        }

        httpRequest('DELETE',`/api/articles/${id}`, null, success, fail);
    });
}
//댓글 작성
const writeComment = document.getElementById('writeComment');
if (writeComment) {
    writeComment.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        body = JSON.stringify({
            comment: document.getElementById('comment').value,
            email: document.getElementById('user-id').value
        });
        function success() {
            alert('댓글 등록 성공');
            location.replace(`/articles/${id}`);
        }
        function fail() {
            alert(JSON.stringify(error));
            location.replace(`/articles/${id}`);
        }
       httpRequest('POST', `/api/articles/${id}/comment` , body, success, fail);
    });
}


// 수정 기능
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search); // 파라미터에서 값 찾기
        let id = params.get('id');

        body = JSON.stringify({ //JavaScript 객체나 배열을 JSON 문자열로 변환하는 메서드입니다
            title: document.getElementById('title').value,
            content: document.getElementById('content').value
        })
        function success() {
            alert('수정 완료되었습니다.');
            location.replace(`/articles/${id}`);
        }

        function fail() {
            alert('수정 실패했습니다.');
            location.replace(`/articles/${id}`);
        }

        httpRequest('PUT',`/api/articles/${id}`, body, success, fail);
    });
}
// 유저 정보 수정하기 버튼
const UserModifyButton = document.getElementById('modify-userInfo');
if (UserModifyButton) {
    UserModifyButton.addEventListener('click', event => {
        let userEmail = document.getElementById('userEmail').value;
        let userReason = document.getElementById('userReason').value;
        let year = document.getElementById('year').value;
        body = JSON.stringify({
            email: userEmail,
            reason: userReason,
            year: year
        });
        function success(){
            alert('정보 수정 완료되었습니다.');
            location.reload();
        }
        function fail(){
            alert('ERROR');
            location.reload();
        }
        httpRequest('PUT' , '/api/myProFil' , body , success , fail);
    });
}

// 생성 기능
const createButton = document.getElementById('create-btn');

if (createButton) {
    // 등록 버튼을 클릭하면 /api/articles로 요청을 보낸다
    createButton.addEventListener('click', event => {
        body = JSON.stringify({
            title: document.getElementById('title').value,
            content: document.getElementById('content').value
        });
        function success() {
            alert('등록 완료되었습니다.');
            location.replace('/articles');
        };
        function fail() {
            alert('등록 실패했습니다.');
            location.replace('/articles');
        };

        httpRequest('POST','/api/articles', body, success, fail)
    });
}


// 쿠키를 가져오는 함수
function getCookie(key) {
    var result = null;
    var cookie = document.cookie.split(';');
    cookie.some(function (item) {
        item = item.replace(' ', '');

        var dic = item.split('=');

        if (key === dic[0]) {
            result = dic[1];
            return true;
        }
    });

    return result;
}

// HTTP 요청을 보내는 함수
function httpRequest(method, url, body, success, fail) {
    fetch(url, {
        method: method,
        headers: { // 로컬 스토리지에서 액세스 토큰 값을 가져와 헤더에 추가
            Authorization: 'Bearer ' + localStorage.getItem('access_token'),
            'Content-Type': 'application/json',
        },
        body: body,
    }).then(response => {
        alert(url);
        if (response.status === 200 || response.status === 201) {
            return success();
        }
        const refresh_token = getCookie('refresh_token');
        if (response.status === 401 && refresh_token) {
            fetch('/api/token', {
                method: 'POST',
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('access_token'),
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    refreshToken: getCookie('refresh_token'),
                }),
            })
                .then(res => {
                    if (res.ok) {
                        return res.json();
                    }
                })
                .then(result => { // 재발급이 성공하면 로컬 스토리지값을 새로운 액세스 토큰으로 교체
                    localStorage.setItem('access_token', result.accessToken);
                    httpRequest(method, url, body, success, fail);
                })
                .catch(error => fail());
        } else {
            return fail();
        }
    });
}