
//댓글 작성
const writeComment = document.getElementById('writeComment');

if (writeComment) {
    writeComment.addEventListener('click', event => {
        if(!isAuthenticated){
            alert("로그인 후 이용해주세요.");
            return;
        }
        let id = document.getElementById('article-id').value;
        body = JSON.stringify({
            comment: document.getElementById('comment').value
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


//댓글 수정 [수정본]
document.addEventListener('DOMContentLoaded', function() {
    const isAuthenticated = document.getElementById('user-data');
    // 수정 버튼
    document.querySelectorAll('.modify-comment-btn').forEach(function(ModifyButton) {
        ModifyButton.addEventListener('click', function() {

            const commentBlock = this.closest('.comment-list');

            const deleteButton = commentBlock.querySelector('.delete-comment-btn');
            const saveButton = commentBlock.querySelector('.save-comment-btn');
            const cancelButton = commentBlock.querySelector('.cancel-comment-btn');

            const commentContent = commentBlock.querySelector('.comment-content');
            const commentInput = commentBlock.querySelector('.comment-input');

            // 삭제 버튼과 수정 버튼 숨기기
            deleteButton.style.display = 'none';
            ModifyButton.style.display = 'none';
            saveButton.style.display = 'inline-block';
            cancelButton.style.display = 'inline-block';

            commentInput.value = commentContent.textContent;
            commentContent.style.display = 'none';
            commentInput.style.display = 'inline-block';
            commentInput.style.border = '1px solid #ccc';
            commentInput.style.borderRadius = '20px';
            commentInput.style.padding = '5px';
            commentInput.style.width = '1000px';
        });
    });
    // 완료 버튼
    document.querySelectorAll('.save-comment-btn').forEach(function(saveButton) {
        saveButton.addEventListener('click', function() {
            const commentBlock = this.closest('.comment-list');
            const commentIdInput = commentBlock.querySelector('input[type="hidden"]');
            const commentInput = commentBlock.querySelector('.comment-input');

            let id = commentIdInput.value;
            let modifications = commentInput.value;

            const body = JSON.stringify({
               comment: modifications,
               id: id
            });

            function success() {
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

            function fail() {
                alert("ERROR");
                location.reload();
            }

            httpRequest('PUT', `/api/comment/${id}`, body, success, fail);
        });
    });
    //취소 버튼
    document.querySelectorAll('.cancel-comment-btn').forEach(function(cancelButton) {
            cancelButton.addEventListener('click', function() {
                const commentBlock = this.closest('.comment-list');

                const modifyButton = commentBlock.querySelector('.modify-comment-btn');
                const deleteButton = commentBlock.querySelector('.delete-comment-btn');
                const saveButton = commentBlock.querySelector('.save-comment-btn');

                const commentContent = commentBlock.querySelector('.comment-content');
                const commentInput = commentBlock.querySelector('.comment-input');

                // 수정, 삭제 버튼 표시, 저장 및 취소 버튼 숨김
                modifyButton.style.display = 'inline-block';
                deleteButton.style.display = 'inline-block';
                saveButton.style.display = 'none';
                this.style.display = 'none';

                // 입력 필드 숨기고, 원래 댓글 내용 표시
                commentContent.style.display = 'block';
                commentInput.style.display = 'none';
            });
        });
    //삭제 버튼
    document.querySelectorAll('.delete-comment-btn').forEach(function(deleteButton) {
        deleteButton.addEventListener('click', function() {
            const commentBlock = this.closest('.comment-list');
            let id = commentBlock.querySelector('input[type="hidden"]').value;

            swal.fire({
                title: "삭제하시겠습니까?",
                icon: "question",
                showCancelButton: true,
                confirmButtonColor: "#6495ed",
                cancelButtonColor: "#d33",
                confirmButtonText: "Yes"
            }).then((result) => {
                if (result.isConfirmed) {
                    const body = JSON.stringify({ id: id });

                    function success() {
                        swal.fire("성공!");
                        setTimeout(() => {
                            location.reload();
                        }, 1000);
                    }

                    function fail() {
                        alert('ERROR');
                        location.reload();
                    }

                    httpRequest('DELETE', `/api/comment/${id}`, body, success, fail);
                }
            });
        });
    });
    document.querySelectorAll('.Like-comment-btn').forEach(function(commentLikeButton){
        commentLikeButton.addEventListener('click', function() {
            const commentBlock = this.closest('.comment-list');
            let id = commentBlock.querySelector('input[type="hidden"]').value;
            function success(){
                location.reload();
            }
            function fail(){
                location.reload();
            }
            httpRequest('POST',`/api/likes/up/comment/${id}`,null, success, fail);
        });
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
            return fail();
        }
    });
}