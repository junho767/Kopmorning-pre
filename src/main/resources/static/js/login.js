const signInBtn = document.getElementById("signIn");
const signUpBtn = document.getElementById("signUp");
const container = document.querySelector(".container");

signInBtn.addEventListener("click", () => {
	container.classList.remove("right-panel-active");
});

signUpBtn.addEventListener("click", () => {
	container.classList.add("right-panel-active");
});

document.getElementById('signupForm').addEventListener('submit', function(event) {
        // Prevent the form from submitting
    event.preventDefault();

        // Get the input values
    const username = document.querySelector('input[name="username"]').value;
    const password = document.querySelector('input[name="password"]').value;
    const email = document.querySelector('input[name="email"]').value;

        // Get the error message div
    const errorMessage = document.getElementById('error-message');

        // Check if any field is empty
    if (!username || !password || !email) {
        alert('빈 칸 없이 기재해주세요.');
        return;
    }
        // If all fields are filled, submit the form
    this.submit();
});