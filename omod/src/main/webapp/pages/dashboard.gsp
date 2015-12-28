
<% ui.decorateWith("appui", "standardEmrPage") %>
<% ui.includeJavascript("skinhelpdesk", "fabric.js") %>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.5/angular.min.js"></script>

        ${ ui.includeFragment("uiframework", "helloUser") }<br>
Sinu  <img src="/openmrs/ms/uiframework/resource/skinhelpdesk/images/i_updown.jpg" alt="Smiley face" height="42" width="42">
<p>
        ${ ui.includeFragment("skinhelpdesk", "encountersToday", [start: "2015-12-27", end: "2015-12-27 23:59:59.999"]) }
</p>

        <% if (context.authenticated) { %>
    And a special hello to you, $context.authenticatedUser.personName.fullName.
            Your roles are:
    <% context.authenticatedUser.roles.findAll { !it.retired }.each { %>
        $it.role ($it.description)<br>
        <% } %>
    <% } else { %>
    You are not logged in.



    <% } %>