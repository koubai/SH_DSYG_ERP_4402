/* dotter.js www.pixelknete.de */
function GE(id){
	return document.getElementById(id);
}
function update_cs(c,target_id){
	GE(target_id).style.background = '#'+c;
}
function upperCase(x) {
	GE(x).value = GE(x).value.toUpperCase()
}
function copy_dc1_to_dc2(){
	if (GE('ndc2').checked){
		GE('dc2').value =  GE('dc1').value;
	}
}
function toggleColorLock(){
	if (GE('ndc2').checked){
		GE('dc2lbl').style.color = '#bbb';
		GE('dc2').style.color = '#bbb';
		GE('dc2').disabled = 'disabled';
		GE('dc2').style.background =  '#e0dfe3'; /*grey*/
		GE('dc2').style.border = '#ccc solid 1px';
		GE('dc2').value =  GE('dc1').value;
		GE('color2').src = 'inc/transDotFFF_disabled.png';
		update_cs(GE('dc1').value,'dcs2');
	}
	else {
		GE('dc2lbl').style.color = '#000';
		GE('dc2').style.color = '#000';
		GE('dc2').style.background =  '#fff';
		GE('dc2').style.border = '#aaa solid 1px';
		GE('dc2').disabled = '';
		GE('color2').src = 'inc/transDotFFF.png';
	}
}
function copy_bc1_to_bc2(){
	if (GE('nbc2').checked){
		GE('bc2').value =  GE('bc1').value;
	}
}
function toggleBGColorLock(){
	if (GE('nbc2').checked){
		GE('bc2lbl').style.color = '#bbb';
		GE('bc2').style.color = '#bbb';
		GE('bc2').disabled = 'disabled';
		GE('bc2').style.background =  '#e0dfe3'; /*grey*/
		GE('bc2').style.border = '#ccc solid 1px';
		GE('bc2').value =  GE('bc1').value;
		GE('color4').src = 'inc/transBgndFFF_disabled.png';
		update_cs(GE('bc1').value,'bcs2');
	}
	else {
		GE('bc2lbl').style.color = '#000';
		GE('bc2').style.color = '#000';
		GE('bc2').style.background =  '#fff';
		GE('bc2').style.border = '#aaa solid 1px';
		GE('bc2').disabled = '';
		GE('color4').src = 'inc/transBgndFFF.png';
	}
}
function preloadImg() {
	document.preload = new Array();
	if(document.images) {
		for(var i = 0; i < preloadImg.arguments.length; i++) {
			document.preload[i] = new Image();
			document.preload[i].src = preloadImg.arguments[i];
		}
	}
}
function init() {
	// alert('alles geladen');
	//GE('preloader').style.display = "none";
	GE('preloader').style.visibility = "hidden";
}