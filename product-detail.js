const productImage = document.querySelector( '.product-image')
const imgs = document.getElementsByTagName('img');
const btnLeft = document.querySelector('.btn-left');
const btnRight = document.querySelector('.btn-right');
const length = imgs.length;
let current = 0;

const handleChangesSlide = () =>{
    if(current === length - 1){
        current =0;
        let width = imgs[0].offsetWidth;
        productImage.style.transform = `translateX(${0}px)`;
        document.querySelector('.active').classList.remove('active');
        document.querySelector('.index-item-'+ current).classList.add('active');
    }else {
        current++
        let width = imgs[0].offsetWidth;
        productImage.style.transform = `translateX(${width * -1 * current}px)`;
        document.querySelector('.active').classList.remove('active');
        document.querySelector('.index-item-'+ current).classList.add('active');
    }
}

let handleEventChangeSlide = setInterval(handleChangesSlide,4000)

btnRight.addEventListener('click', () => {
    clearInterval(handleEventChangeSlide);
    handleChangesSlide()
    handleEventChangeSlide = setInterval(handleChangesSlide,4000)
})

btnLeft.addEventListener('click', () => {
    clearInterval(handleEventChangeSlide);
    if(current === 0){
        current = length - 1;
        let width = imgs[0].offsetWidth;
        productImage.style.transform = `translateX(${width * -1 * current}px)`;
        document.querySelector('.active').classList.remove('active');
        document.querySelector('.index-item-'+ current).classList.add('active');
    }else {
        current--
        let width = imgs[0].offsetWidth;
        productImage.style.transform = `translateX(${width * -1 * current}px)`;
        document.querySelector('.active').classList.remove('active');
        document.querySelector('.index-item-'+ current).classList.add('active');
    }
    handleEventChangeSlide = setInterval(handleChangesSlide,4000)
})
