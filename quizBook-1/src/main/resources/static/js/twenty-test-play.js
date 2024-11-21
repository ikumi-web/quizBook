


document.addEventListener("DOMContentLoaded", () => {

	//Are you Ready?Go!の文字表示▼▼▼▼▼▼▼▼
	const messageDiv = document.querySelector(".message");
	setTimeout(() => {
		messageDiv.style.display = 'none';
	}, 5000);
	messageDiv.innerHTML = 'Are you Ready?';
	messageDiv.style.display = 'block';
	setTimeout(() => {
		messageDiv.innerHTML = 'Go!!!';
	}, 3000);
	const messageBackDiv = document.querySelector(".message-back");
	setTimeout(() => {
		messageBackDiv.style.display = 'none';
	}, 5000);
	//Are you Ready?Go!の文字表示▲▲▲▲▲▲▲▲
	const minutesElem = document.querySelector(".minutes");
	const secondsElem = document.querySelector(".seconds");
	let minutes = parseInt(minutesElem.textContent, 10);
	let seconds = parseInt(secondsElem.textContent, 10);
	const secondsMill = minutes * 60 * 1000;

	function countdown() {
		if (minutes > 0 || seconds > 0) {
			if (seconds === 0) {
				minutes--;
				seconds = 59;
			} else {
				seconds--;
			}
			minutesElem.textContent = minutes < 10 ? '0' + minutes : minutes;
			secondsElem.textContent = seconds < 10 ? '0' + seconds : seconds;
		} else {
			console.log("countdown終了");
		}
	}
	//カウントダウン。カウントダウン後止まる
	setTimeout(() => {
		const interval = setInterval(countdown, 1000)
		setInterval(() => {
			clearInterval(interval);
		}, secondsMill);
	},4000);

});