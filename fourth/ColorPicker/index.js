window.addEventListener('DOMContentLoaded', () => {
    function colorPicker() {
        let items = document.querySelectorAll('.styled-changer .btn'),
            colors = [],
            index = 0;
    
        function init(){
            items.forEach(item => {
                colors.push(window.getComputedStyle(item).backgroundColor);
            });
    
            document.querySelector('body').style.backgroundColor = colors[0];
    
            changerBackground();
        }
        
        function changerBackground(){
            let changer = document.querySelector('.changer');
            changer.style.backgroundColor = index === colors.length - 1 ? colors[0] : colors[index + 1];
        }
    
        function styledChanger(){
            let styledChanger = document.querySelector('.styled-changer');
    
            styledChanger.addEventListener('click', (event) => {
                let target = event.target;
                if(target && target.classList.contains('btn')){
                    let color = window.getComputedStyle(target).backgroundColor;
                    document.querySelector('body').style.backgroundColor = color;
                    indexUpdate(color);
                    changerBackground();
                }
            });
    
            function indexUpdate(color){
                colors.forEach((item, i) => {
                    if(color === item){
                        index = i;
                    }
                });
            }
        }
    
        function staticChanger(){
            let staticChanger = document.querySelector('.changer');
    
            staticChanger.addEventListener('click', () => {
                document.querySelector('body').style.backgroundColor = nextColor();
                changerBackground();
            });
        }
    
        function nextColor(){
            index++;
            if(index === colors.length){
                index = 0;
            }
    
            return colors[index];
        }
    
        init();
        staticChanger();
        styledChanger();
    }
    
    colorPicker();
});