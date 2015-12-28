${ ui.includeFragment("uiframework", "helloUser") }<br>
Sinu
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