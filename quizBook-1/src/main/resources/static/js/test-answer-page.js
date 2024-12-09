/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	
	
// 正答率を設定する関数
function updateProgressBar(percentage) {
  const progressBar = document.getElementById('progress-bar');
 
  // 正答率に応じて幅を設定
  progressBar.style.width = percentage + '%';
  if(percentage <= 33){
	  percentageLabel.classList.add('lowScore');
  }else if(percentage >= 80){
	  percentageLabel.classList.add('hightScore');
  }

}

const percentageLabel = document.querySelector('.percentage-label');
const percentage = percentageLabel.getAttribute('data-value');
updateProgressBar(percentage); // 正答率%に更新





});


