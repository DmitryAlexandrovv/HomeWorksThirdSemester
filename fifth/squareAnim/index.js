let square = document.querySelector('.square'),
    frameHeight = 2,
    frameWidth = 2;

function toLeftTop(){
    let interval = setInterval(() => {
        square.style.left = parseInt(square.style.left) - frameWidth + 'px';
        square.style.top = parseInt(square.style.top) - frameHeight + 'px';
        if(parseInt(square.style.left) <= 0){
            clearInterval(interval);
            toRightTop();
            return;
        }
        if(parseInt(square.style.top) <= 0){
            clearInterval(interval);
            toLeftBottom();
            return;
        }
    }, 5);
}

function toRightTop(){
    let interval = setInterval(() => {
        square.style.left = parseInt(square.style.left) + frameWidth + 'px';
        square.style.top = parseInt(square.style.top) - frameHeight + 'px';
        if(parseInt(square.style.left) >= document.body.clientWidth - parseInt(square.style.width)){
            clearInterval(interval);
            toLeftTop();
            return;
        }
        if(parseInt(square.style.top) <= 0){
            clearInterval(interval);
            toRightBottom();
            return;
        }
    }, 5);
}

function toLeftBottom(){
    let interval = setInterval(() => {
        square.style.left = parseInt(square.style.left) - frameWidth + 'px';
        square.style.top = parseInt(square.style.top) + frameHeight + 'px';
        if(parseInt(square.style.left) <= 0){
            clearInterval(interval);
            toRightBottom();
            return;
        }
        if(parseInt(square.style.top) >= document.body.clientHeight - parseInt(square.style.height)){
            clearInterval(interval);
            toLeftTop();
            return;
        }
    }, 5);
}

function toRightBottom(){
    let interval = setInterval(() => {
        square.style.left = parseInt(square.style.left) + frameWidth + 'px';
        square.style.top = parseInt(square.style.top) + frameHeight + 'px';
        if(parseInt(square.style.left) >= document.body.clientWidth - parseInt(square.style.width)){
            clearInterval(interval);
            toLeftBottom();
            return;
        }
        if(parseInt(square.style.top) >= document.body.clientHeight - parseInt(square.style.height)){
            clearInterval(interval);
            toRightTop();
            return;
        }
    }, 5);
}

toLeftBottom();