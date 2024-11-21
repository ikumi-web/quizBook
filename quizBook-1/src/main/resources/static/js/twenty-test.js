/**
 * 
 */


document.addEventListener("DOMContentLoaded", () => {


	const digits = [0, 0, 0]
	const maxDigit = 9;
	const minDigit = 0;

	const digitButtonQuiz = document.querySelectorAll(".digit-button-quiz");
	digitButtonQuiz.forEach((button) => {
		button.addEventListener("click", (event) => {
			const index = parseInt(event.target.dataset.index, 10);
			const isIncrease = event.target.classList.contains("increase");

			if (isIncrease && digits[index] < maxDigit) {
				digits[index]++;
			} else if (!isIncrease && digits[index] > minDigit) {
				digits[index]--;
			}

			document.getElementById(`digit-${index}`).value = digits[index];

		});
	});
	const digitsTime = [0, 0, 0]
	const maxTimeDigit = 9;
	const minTimeDigit = 0;

	const digitButtonTime = document.querySelectorAll(".digit-button-time");
	digitButtonTime.forEach((button) => {
		button.addEventListener("click", (event) => {
			const index = parseInt(event.target.dataset.index, 10);
			const isIncrease = event.target.classList.contains("increase");

			if (isIncrease && digitsTime[index] < maxTimeDigit) {
				digitsTime[index]++;
			} else if (!isIncrease && digitsTime[index] > minTimeDigit) {
				digitsTime[index]--;
			}

			document.getElementById(`digit-time-${index}`).value = digitsTime[index];

		});
	});
	
});