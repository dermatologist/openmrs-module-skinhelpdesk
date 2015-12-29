<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300|Playfair+Display:400italic' rel='stylesheet' type='text/css' />
<link href='http://fonts.googleapis.com/css?family=Plaster' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>


<!-- Include Custom CSS -->
<% ui.includeCss("skinhelpdesk", "skinhelpdesk.css") %>
<% ui.includeCss("skinhelpdesk", "kitchensink.css") %>

<!-- Include Custom Scripts -->
<% ui.includeJavascript("skinhelpdesk", "angular.min.js") %>
<% ui.includeJavascript("skinhelpdesk", "fabric.js") %>
<% ui.includeJavascript("skinhelpdesk", "paster.js") %>
<% ui.includeJavascript("skinhelpdesk", "lesionmap.js") %>
<% ui.includeJavascript("skinhelpdesk", "canvassaver.js") %>
<% ui.includeJavascript("skinhelpdesk", "font_definitions.js") %>
<% ui.includeJavascript("skinhelpdesk", "utils.js") %>
<% ui.includeJavascript("skinhelpdesk", "app_config.js") %>
<% ui.includeJavascript("skinhelpdesk", "controller.js") %>


<script>

        function makeid()
        {
            var text = "";
            var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

            for( var i=0; i < 5; i++ )
                text += possible.charAt(Math.floor(Math.random() * possible.length));

            return text;
        }

sessionStorage.randomFile1 = makeid();
sessionStorage.randomFile2 = makeid();

jQuery(document).ready(function() {
    addWatermark();
    jQuery('#virtualderm').hide();
    jQuery('#vdbutton').fadeOut();
    var timer = setTimeout(function() { jQuery("#FormLoad").click(); }, 2000);

    //##### Add record when Add Record Button is clicked #########
    jQuery("#FormSave").click(function (e) {

        e.preventDefault();

        //rasterizeJSON();

        var input = getLocation(); //post variables
        var doit = "doit=1";
        input = "input="+input;
        //var consultation_category_id = "consultation_category_id="+"";
        var myData = doit+"&"+input;

        jQuery.ajax({
            type: "POST", // HTTP method POST or GET
            url: "ajax_lesionmapper.php", //Where to make Ajax calls
            dataType:"text", // Data type, HTML, json etc.
            data:myData, //post variables
            success:function(response){
                jQuery("#responds").empty();
                var url1 = 'http://skinhelpdesk.com/lesionmap.php?lesionid='+response;
                var message1 = '<p><strong>The lesionMap is stored with the ID: </strong>'+response+'<br>URL: '+url1+'</p>';
                jQuery("#responds").append(message1);
                window.prompt("Copy to clipboard: Ctrl+C, Enter", url1);
                jQuery('#vdbutton').fadeIn();
                //alert(response);
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(thrownError); //throw any errors
            }
        });
    });

    jQuery("#sendVD").click(function (e) {

        e.preventDefault();

        //rasterizeJSON();

        var input2 = getLocation(); //post variables
        jQuery('#history').val(cdss(input2));  //cdss is a function in lesionmap.js
        jQuery("#consult").click();
        jQuery('#vdbutton').fadeOut();


    });

    jQuery("#FormLoad").click(function (e) {

        e.preventDefault();


        var input = "input="+ jQuery("#lesionid").val(); //post variables
        var doit = "doit=2";
        //var consultation_category_id = "consultation_category_id="+"";
        var myData = doit+"&"+input;


        jQuery.ajax({
            type: "POST", // HTTP method POST or GET
            url: "ajax_lesionmapper.php", //Where to make Ajax calls
            dataType:"text", // Data type, HTML, json etc.
            data:myData, //post variables
            success:function(response){
                response = response.replace(/\\"/g, '"');
                setLocation(response);
                jQuery("#FormDisplay").click();
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(thrownError); //throw any errors
            }
        });
    });
});

</script>

<script>
var cs = new CanvasSaver('script/saveme.php');
var cnvs = document.getElementById('canvas');
var btn = cs.generateButton('Download image', cnvs, 'lesionmap');
document.getElementById('saveexample').appendChild(btn);
</script>


<div ng-app="kitchensink">
   <div style="position:relative;width:704px;float:left;" id="canvas-wrapper" ng-controller="CanvasControls">
        <div id="saveexample">
            <canvas id="canvas" width="600" height="500"></canvas>
        </div>

        <div id="color-opacity-controls" ng-show="canvas.getActiveObject()">
            <label for="opacity">Opacity: </label>
            <input value="100" type="range" bind-value-to="opacity">
            <label for="color" style="margin-left:10px">Color: </label>
            <input type="color" style="width:40px" bind-value-to="fill">
        </div>

        <div id="text-wrapper" style="margin-top: 10px" ng-show="getText()">
                    <textarea bind-value-to="text"></textarea>
                    <div id="text-controls"></div>
        </div>




        <div id="commands" ng-click="maybeLoadShape(\$event)">
                <p>Add
                    <strong>lesions</strong> to canvas:
                </p>
                <p>
                    <button type="button" id ="papule" class="btn-xlarge" ng-click="addImage1()">Papule</button>
                    <button type="button" id ="hypopigmented" class="btn-xlarge" ng-click="addImage2()">(Hypo)Macule</button>
                    <button type="button" id ="hyperpigmented" class="btn-xlarge" ng-click="addImage2a()">(Hyper)Macule</button>
                    <button type="button" id ="nodule" class="btn-xlarge" ng-click="addImage3()">Nodule</button>
                    <button type="button" id ="plaque" class="btn-xlarge" ng-click="addImage4()">Plaque</button>
                    <button type="button" id ="vesicle" class="btn-xlarge" ng-click="addImage5()">Vesicle</button>
                    <button type="button" id ="bulla" class="btn-xlarge" ng-click="addImage6()">Bulla</button>
                    <br>
                        <button type="button" id ="pustule" class="btn-xlarge" ng-click="addImage7()">Pustule</button>
                        <button type="button" id ="telangiectasia" class="btn-xlarge" ng-click="addImage8()">telangiectasia</button>
                        <button type="button" id ="scaling" class="btn-xlarge" ng-click="addImage9()">Scaling</button>
                        <button type="button" id ="crusting" class="btn-xlarge" ng-click="addImage10()">Crusting</button>
                        <button type="button" id ="atrophic" class="btn-xlarge" ng-click="addImage11()">Atrophic</button>
                        <button type="button" id ="lichenification" class="btn-xlarge" ng-click="addImage12()">lichenification</button>
                        <button type="button" id ="erosion" class="btn-xlarge" ng-click="addImage13()">Erosion</button>
                        <br>
                            <button type="button" id ="excoriation" class="btn-xlarge" ng-click="addImage14()">Excoriation</button>
                            <button type="button" id ="fissure" class="btn-xlarge" ng-click="addImage15()">Fissure</button>
                            <button type="button" id ="ulcer" class="btn-xlarge" ng-click="addImage16()">Ulcer</button>
                            <button type="button" id ="scar" class="btn-xlarge" ng-click="addImage17()">Scar</button>
                            <button type="button" id ="purpura" class="btn-xlarge" ng-click="addImage18()">Purpura</button>
                            <button type="button" id ="user1" class="btn-xlarge" ng-click="addImageUser1()">User1</button>
                            <button type="button" id ="user2" class="btn-xlarge" ng-click="addImageUser2()">User2</button>
                            <br>
                                <button type="button" id ="i_updown" class="btn-xlarge" ng-click="addImage20()">Up/Down</button>
                                <button type="button" id ="i_koebner" class="btn-xlarge" ng-click="addImage21()">Koebner</button>
                                <button type="button" id ="i_progress" class="btn-xlarge" ng-click="addImage22()">Migrate</button>
                                <button type="button" id ="i_photosensitive" class="btn-xlarge" ng-click="addImage23()">Photosensitive</button>
                                <button type="button" id ="i_centrifugal" class="btn-xlarge" ng-click="addImage24()">Centrifugal</button>
                                <button type="button" id ="i_started" class="btn-xlarge" ng-click="addImage25()">Started</button>
                                <button type="button" id ="i_central" class="btn-xlarge" ng-click="addImage26()">Central</button>
                </p>
                <p>
                    <input name="lesionid" id="lesionid" value="">
                    <button type="button" class="button" id="FormLoad">Load</button>
                    <div id="FormDisplay" ng-click="loadSVG()"></div>
                </p>
	</div><!-- commands -->




        <div id="global-controls">
        	<p><button class="button" id="FormSave" ng-click="rasterizeJSON()">SAVE</button></p>
        </div>

	<p>
		<div id="responds"></div>
                <button class="button" ng-click="addText()">Add text</button>
        </p>

	<p>
               	<a href="#" id="addimage1" target="_blank" class="button">Upload Image 1</a>
                <a href="#" id="addimage2" target="_blank" class="button">Upload Image 2</a>
                <br><small>It is the users responsibility to ensure that the image after cropping the lesion is not personally identifiable.</small>
                <script>
			var a = document.getElementById("addimage1");
			a.href = "addimage.php?randomFile=" + encodeURIComponent(sessionStorage.getItem("randomFile1"));
			var b = document.getElementById("addimage2");
			b.href = "addimage.php?randomFile=" + encodeURIComponent(sessionStorage.getItem("randomFile2"));
		</script>
        </p>

	<p>
        	<button class="button [secondary success alert]" ng-click="confirmClear()">Clear canvas</button>
        </p>


	<div style="margin-top:10px;" id="drawing-mode-wrapper">
        	<button id="drawing-mode" class="button"
          		ng-click="setFreeDrawingMode(!getFreeDrawingMode())"
          		ng-class="{'btn-inverse': getFreeDrawingMode()}">
          			{[ getFreeDrawingMode() ? 'Exit free drawing mode' : 'Enter free drawing mode' ]}
        	</button>

		<div id="drawing-mode-options" ng-show="getFreeDrawingMode()">
                	<label for="drawing-mode-selector">Mode:</label>
                                            <select id="drawing-mode-selector" bind-value-to="drawingMode">
                                                <option>Pencil</option>
                                                <option>Circle</option>
                                                <option>Spray</option>
                                                <option>Pattern</option>
                                                <option>hline</option>
                                                <option>vline</option>
                                                <option>square</option>
                                                <option>diamond</option>
                                                <option>texture</option>
                                            </select><br>
			<label for="drawing-line-width">Line width:</label>
			<input type="range" value="30" min="0" max="150" bind-value-to="drawingLineWidth"><br>
			<label for="drawing-color">Line color:</label>
			<input type="color" value="#005E7A" bind-value-to="drawingLineColor"><br>
			<label for="drawing-shadow-width">Line shadow width:</label>
			<input type="range" value="0" min="0" max="50" bind-value-to="drawingLineShadowWidth">
                </div><!-- DRAWING MODE OPTION-->
        </div><!-- DRAWING MODE WRAPPER-->
   </div>
</div><!-- ng-app kitchensink -->

<script>
  var kitchensink = { };
  var canvas = new fabric.Canvas('canvas');
  canvas.setBackgroundImage('/openmrs/ms/uiframework/resource/skinhelpdesk/lesions/body.jpeg',        canvas.renderAll.bind(canvas));

</script>

<script>

  (function() {

    if (document.location.hash !== '#zoom') return;

    function renderVieportBorders() {
      var ctx = canvas.getContext();

      ctx.save();

      ctx.fillStyle = 'rgba(0,0,0,0.1)';

      ctx.fillRect(
        canvas.viewportTransform[4],
        canvas.viewportTransform[5],
        canvas.getWidth() * canvas.getZoom(),
        canvas.getHeight() * canvas.getZoom());

      ctx.setLineDash([5, 5]);

      ctx.strokeRect(
        canvas.viewportTransform[4],
        canvas.viewportTransform[5],
        canvas.getWidth() * canvas.getZoom(),
        canvas.getHeight() * canvas.getZoom());

      // var viewport = canvas.getViewportCenter();
      //console.log(canvas.getZoom(), viewport.x, viewport.y);

      ctx.restore();
    }

    jQuery(canvas.getElement().parentNode).on('wheel mousewheel', function(e) {

      // canvas.setZoom(canvas.getZoom() + e.originalEvent.wheelDelta / 300);

      var newZoom = canvas.getZoom() + e.originalEvent.wheelDelta / 300;
      canvas.zoomToPoint({ x: e.offsetX, y: e.offsetY }, newZoom);

      renderVieportBorders();

      return false;
    });

    var viewportLeft = 0,
        viewportTop = 0,
        mouseLeft,
        mouseTop,
        _drawSelection = canvas._drawSelection,
        isDown = false;

    canvas.on('mouse:down', function(options) {
      isDown = true;

      viewportLeft = canvas.viewportTransform[4];
      viewportTop = canvas.viewportTransform[5];

      mouseLeft = options.e.x;
      mouseTop = options.e.y;

      if (options.e.altKey) {
        _drawSelection = canvas._drawSelection;
        canvas._drawSelection = function(){ };
      }

      renderVieportBorders();
    });

    canvas.on('mouse:move', function(options) {
      if (options.e.altKey && isDown) {
        var currentMouseLeft = options.e.x;
        var currentMouseTop = options.e.y;

        var deltaLeft = currentMouseLeft - mouseLeft,
            deltaTop = currentMouseTop - mouseTop;

        canvas.viewportTransform[4] = viewportLeft + deltaLeft;
        canvas.viewportTransform[5] = viewportTop + deltaTop;

        console.log(deltaLeft, deltaTop);

        canvas.renderAll();
        renderVieportBorders();
      }
    });

    canvas.on('mouse:up', function() {
      canvas._drawSelection = _drawSelection;
      isDown = false;
    });
  })();

</script>

    <script>
      (function(){
        var mainScriptEl = document.getElementById('main');
        if (!mainScriptEl) return;
        var preEl = document.createElement('pre');
        var codeEl = document.createElement('code');
        codeEl.innerHTML = mainScriptEl.innerHTML;
        codeEl.className = 'language-javascript';
        preEl.appendChild(codeEl);
        document.getElementById('bd-wrapper').appendChild(preEl);
      })();
    </script>

    <script>
(function() {
  fabric.util.addListener(fabric.window, 'load', function() {
    var canvas = this.__canvas || this.canvas,
        canvases = this.__canvases || this.canvases;

    canvas && canvas.calcOffset && canvas.calcOffset();

    if (canvases && canvases.length) {
      for (var i = 0, len = canvases.length; i < len; i++) {
        canvases[i].calcOffset();
      }
    }
  });
})();
</script>

