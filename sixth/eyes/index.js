let left = document.querySelector('.left');
let right = document.querySelector('.right');
let leftWrapper = document.querySelector('.left-wrapper');
let rightWrapper = document.querySelector('.right-wrapper');

let left_center_x = left.getBoundingClientRect().left;
let left_center_y = left.getBoundingClientRect().top;

let right_center_x = right.getBoundingClientRect().left;
let right_center_y = right.getBoundingClientRect().top;

window.addEventListener('mousemove', (event) => {
    let mouse_x = event.x;
    let mouse_y = event.y;

    let xLeft = mouse_x - left_center_x;
    let yLeft = mouse_y - left_center_y;

    let xRight = mouse_x - right_center_x;
    let yRight = mouse_y - right_center_y;

    function getDeg(x, y){
        if((x > 0 && y > 0) || (x > 0 && y < 0)){
            return (180 / Math.PI) * Math.atan(y / x);
        } else if((x < 0 && y < 0) || (x < 0 && y > 0)){
            return (180 / Math.PI) * (Math.PI + Math.atan(y / x));
        }
    }

    leftWrapper.style.transform = 'rotate(' + getDeg(xLeft, yLeft) + 'deg)';
    rightWrapper.style.transform = 'rotate(' + getDeg(-xRight, -yRight) + 'deg)';
});