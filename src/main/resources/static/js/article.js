// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        Swal.fire({
            title: "삭제하시겠습니까?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes"
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "삭제 완료!",
                    icon: "success",
                    showConfirmButton:false
                });
           }
           setTimeout(() => {
                httpRequest('DELETE', `/api/articles/${id}`, null, success, fail);
           }, 1500);
        });
        function success() {
            location.replace('/articles');
        }
        function fail() {
            alert('삭제 실패했습니다.');
            location.replace('/articles');
        }
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
            location.reload();
        }
        function fail() {
            location.reload();
        }
       httpRequest('POST', `/api/articles/${id}/comment` , body, success, fail);
    });
}
//댓글 삭제
const DeleteComment = document.getElementById('delete-comment');
if(DeleteComment) {
    DeleteComment.addEventListener('click', event => {
        let id = document.getElementById('comment-id').value;
        let body = JSON.stringify({id: id});
        swal.fire({
            title: "삭제하시겠습니까?",
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#6495ed",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes"
        }).then((result) => {
            if(result.isConfirmed){
                httpRequest('DELETE', `/api/comment/${id}`,body,success,fail);
            }
        })
       function success() {
            location.reload();
        }
        function fail() {
            alert('ERROR');
            location.reload();
        }
    });
}
//댓글 수정
const ModifyComment = document.getElementById('modify-comment');
const deleteComment = document.getElementById('delete-comment');
const saveComment = document.getElementById('save-comment');
const commentContent = document.getElementById('comment-content');
const commentInput = document.getElementById('comment-input');
const cancelComment = document.getElementById('cancel-comment');


if(ModifyComment) {
    ModifyComment.addEventListener('click', event => {

        deleteComment.style.display = 'none';
        ModifyComment.style.display = 'none';
        saveComment.style.display = 'inline-block';
        cancelComment.style.display = 'inline-block';

        commentInput.value = commentContent.textContent;
        commentContent.style.display = 'none';
        commentInput.style.display = 'inline-block';
        commentInput.style.border = '1px solid #ccc';
        commentInput.style.borderRadius = '20px';
        commentInput.style.padding = '5px';
        commentInput.style.width = '500px';

    });
}
if(saveComment){
    saveComment.addEventListener('click', event => {
        let Modifications = document.getElementById('comment-input').value;
        let id = document.getElementById('comment-id').value;
        body = JSON.stringify({
            comment : Modifications,
            id : id
        });
        function success(){
            swal.fire({
                title: "성공!",
                icon: "success",
                showCancelButton: false,
                showConfirmButton: false
            });
            setTimeout(() => {
                location.reload();
            }, 1000);
        }
        function fail(){
            alert("Error");
        }
        httpRequest('PUT',`/api/comment/${id}`,body, success, fail);
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
        });
        Swal.fire({
            title: "수정하시겠습니까?",
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#6495ed",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes"
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "수정 완료!",
                    icon: "success",
                    showConfirmButton:false
                });
                setTimeout(() => {
                    httpRequest('PUT',`/api/articles/${id}`, body, success, fail);
                }, 1000);
           }
        });
        function success() {
            location.replace(`/articles/${id}`);
        }
        function fail() {
            alert('ERROR');
            location.replace(`/articles/${id}`);
        }
    });
}
// 유저 정보 수정하기 버튼
const UserModifyButton = document.getElementById('modify-userInfo');
if (UserModifyButton) {
    UserModifyButton.addEventListener('click', event => {
        let nickname = document.getElementById('userNickname').value;
        let reason = document.getElementById('userReason').value;
        let year = document.getElementById('year').value;
        body = JSON.stringify({
            nickname : nickname,
            reason: reason,
            year: year
        });
        function success(){
            Swal.fire({
                position: "center",
                icon: "success",
                title: "정보 수정이 완료 되었습니다.",
                showConfirmButton: false,
                timer: 1500
            }).then(() => {
                location.reload();
            });
        }
        function fail(){
            Swal.fire({
                 position: "center",
                 icon: "error",
                 title: "형식에 맞게 다시 입력해주세요.",
                 showConfirmButton: false,
                 timer: 1500
           }).then(() => {
                 location.reload();
           });
        }
        function checkInputs(nickname, reason, year) {
            if (nickname === null || reason === null || year === null) {
                fail();
            } else {
                // 모든 입력이 유효한 경우 success() 메서드 호출 등 추가 작업 수행
                httpRequest('POST' , '/api/myProFil' , body , success , fail);
            }
        }
        checkInputs(nickname,reason,year);
    });
}

// 좋아요 기능
const likeUpButton = document.getElementById('LikeUp-btn');
if (likeUpButton) {
    likeUpButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        function success(){
            location.reload();
        }
        function fail(){
            location.reload();
        }
        httpRequest('POST',`/api/likes/up/${id}`, null, success, fail)
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
        Swal.fire({
            title: "글을 등록 하시겠습니까?",
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#6495ed",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes"
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "등록 완료!",
                    icon: "success",
                    showConfirmButton:false
                });
           }
           setTimeout(() => {
                httpRequest('POST','/api/articles', body, success, fail)
           }, 1000);
        });
        function success() {
            location.replace('/articles');
        };
        function fail() {
            location.replace('/articles');
        };
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