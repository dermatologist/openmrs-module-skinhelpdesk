
<!-- Include Custom CSS -->
<% ui.includeCss("skinhelpdesk", "skinhelpdesk.css") %>
<% ui.includeCss("skinhelpdesk", "kitchensink.css") %>

<!-- Include Custom Scripts -->
<%
    ui.includeJavascript("uicommons", "angular.js")
    ui.includeJavascript("uicommons", "ngDialog/ngDialog.js")
    ui.includeCss("uicommons", "ngDialog/ngDialog.min.css")
%>
<% ui.includeJavascript("skinhelpdesk", "fabric.js") %>
<% ui.includeJavascript("skinhelpdesk", "paster.js") %>
<% ui.includeJavascript("skinhelpdesk", "font_definitions.js") %>
<% ui.includeJavascript("skinhelpdesk", "utils.js") %>
<% ui.includeJavascript("skinhelpdesk", "app_config.js") %>
<% ui.includeJavascript("skinhelpdesk", "controller.js") %>
<% ui.includeJavascript("skinhelpdesk", "skinhelpdesk.js") %>



<script>


jQuery(document).ready(function() {
    addWatermark();
    var timer = setTimeout(function() { jQuery("#FormLoad").click(); }, 2000);




    //##### Add record when Add Record Button is clicked #########
    jQuery("#FormSave").click(function (e) {

        e.preventDefault();
        var patientid = ${ patient.id };
        var imagemap = getLocation();
        jQuery.post('${ ui.actionLink("putMap") }', { returnFormat: 'json', patientId: patientid, lesionmap: imagemap },
                function (data) {
                    if (data.indexOf("${MESSAGE_SUCCESS}") >= 0) {
                        jq().toastmessage('showSuccessToast', "Image Saved.");
                    } else {
                        jq().toastmessage('showErrorToast', "Error. Please try again after page refresh");
                    }
                })
                .error(function() {
                    notifyError("Programmer error: delete identifier failed");
        })

    });


    jQuery("#FormReset").click(function (e) {

        e.preventDefault();
        var result = confirm("Want to reset? All data on canvas for this patient will be lost!")
        if (result) {
            var patientid = ${ patient.id };
            var imagemap = "-"; //Blank Imagemap
            jQuery.post('${ ui.actionLink("putMap") }', {
                        returnFormat: 'json',
                        patientId: patientid,
                        lesionmap: imagemap
                    },
                    function (data) {
                        if (data.indexOf("${MESSAGE_SUCCESS}") >= 0) {
                            jq().toastmessage('showSuccessToast', "Image Reset.");
                            location.reload();
                        } else {
                            jq().toastmessage('showErrorToast', "Error. Please try again after page refresh");
                        }
                    })
                    .error(function () {
                        notifyError("Programmer error: delete identifier failed");
                    })
        }
    });

    jQuery("#FormLoad").click(function (e) {

        e.preventDefault();
        jQuery.getJSON('${ ui.actionLink("getMap", [returnFormat: "json"]) }', { patientId: ${ patient.id } },

                function(data) {
                    response = data.message;
                    response = response.replace(/\\"/g, '"');
                    setLocation(response);
                    jQuery("#FormDisplay").click();
                });

    });
});

</script>

<div ng-app="kitchensink">
   <div style="position:relative;width:600px;float:left;" id="canvas-wrapper" ng-controller="CanvasControls">
<div class="info-header">
        <i class="icon-camera-retro"></i>
        <h3>LesionMap</h3>
</div>
            <canvas id="canvas" width="600" height="500"></canvas>


        <div id="color-opacity-controls" ng-show="canvas.getActiveObject()">
            <label for="opacity">Opacity: </label>
            <input id="opacity" value="100" type="range" bind-value-to="opacity">
            <label for="color" style="margin-left:10px">Color: </label>
            <input id="color" type="color" style="width:40px" bind-value-to="fill">
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
                            <!--
                            <button type="button" id ="user1" class="btn-xlarge" ng-click="addImageUser1()">User1</button>
                            <button type="button" id ="user2" class="btn-xlarge" ng-click="addImageUser2()">User2</button>
                            -->
                            <br>
                                <button type="button" id ="i_updown" class="btn-xlarge" ng-click="addImage20()">Up/Down</button>
                                <button type="button" id ="i_koebner" class="btn-xlarge" ng-click="addImage21()">Koebner</button>
                                <button type="button" id ="i_progress" class="btn-xlarge" ng-click="addImage22()">Migrate</button>
                                <button type="button" id ="i_photosensitive" class="btn-xlarge" ng-click="addImage23()">Photosensitive</button>
                                <button type="button" id ="i_centrifugal" class="btn-xlarge" ng-click="addImage24()">Centrifugal</button>
                                <button type="button" id ="i_started" class="btn-xlarge" ng-click="addImage25()">Started</button>
                                <button type="button" id ="i_central" class="btn-xlarge" ng-click="addImage26()">Central</button>
                </p>

            <div id="FormLoad"></div>
            <button type="button" class="button" id="FormReset">Reset</button>
                    <button class="button" id="FormSave" ng-click="rasterizeJSON()">SAVE</button>
                    <button class="button" ng-click="addText()">Add text</button>
                	<button class="button [secondary success alert]" ng-click="confirmClear()">Clear canvas</button>

                    <div id="FormDisplay" ng-click="loadSVG()"></div>
        

	       <div id="drawing-mode-wrapper">
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

   	<p><div id="responds"></div></p>

   </div> <!-- canvas-wrapper -->
</div><!-- ng-app kitchensink -->

<script>
  var kitchensink = { };
  var canvas = new fabric.Canvas('canvas');
  canvas.setBackgroundImage('../../ms/uiframework/resource/skinhelpdesk/lesions/body.jpeg',        canvas.renderAll.bind(canvas));

</script>


