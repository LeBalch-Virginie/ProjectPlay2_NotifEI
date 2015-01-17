jQuery(function($) {
    $("#medicament-search").keyup(function() {
        if ($(this).val() != "") {
            $.getJSON(
                "autocomplete/medicament/" + $(this).val(),
                function(data) {
                    $("#medicament-search").autocomplete({
                        source: data
                    });
                }
            );
        }
    });

    $("#medicament-form").submit(function(e) {
        e.preventDefault();
        $("#medicament-effets").html('<li><img src="/assets/images/wait.gif" height="42" width="42" alt="wait" /></li>');
        $.post(
            "/recherche/medicament",
            { "medicament-search" : $("#medicament-search").val() },
            function(data) {
               $("#medicament-effets").empty();
                for (var i = 0; i < data.length; i++) {
                    $("#medicament-effets").append("<li>" + data[i] + "</li>");
                }
            },
            "json"
        );
        return false;
    });

    $("#dispositif-search").keyup(function() {
        if ($(this).val() != "") {
            $.getJSON(
                "autocomplete/dispositif/" + $(this).val(),
                function(data) {
                    $("#dispositif-search").autocomplete({
                        source: data
                    });
                }
            );
        }
    });

    $("#dispositif-form").submit(function(e) {
        e.preventDefault();
        $("#dispositif-effets").html('<li><img src="/assets/images/wait.gif" height="42" width="42" alt="wait" /></li>');
        $.post(
            "/recherche/dispositif",
            { "dispositif-search" : $("#dispositif-search").val() },
            function(data) {
                $("#dispositif-effets").empty();
                for (var i = 0; i < data.length; i++) {
                    $("#dispositif-effets").append("<li>" + data[i] + "</li>");
                }
            },
            "json"
        );
        return false;
    });

    $("#cosmetique-search").keyup(function() {
        if ($(this).val() != "") {
            $.getJSON(
                "autocomplete/cosmetique/" + $(this).val(),
                function(data) {
                    $("#cosmetique-search").autocomplete({
                        source: data
                    });
                }
            );
        }
    });

    $("#cosmetique-form").submit(function(e) {
        e.preventDefault();
        $("#cosmetique-effets").html('<li><img src="/assets/images/wait.gif" height="42" width="42" alt="wait" /></li>');
        $.post(
            "/recherche/cosmetique",
            { "cosmetique-search" : $("#cosmetique-search").val() },
            function(data) {
                $("#cosmetique-effets").empty();
                for (var i = 0; i < data.length; i++) {
                    $("#cosmetique-effets").append("<li>" + data[i] + "</li>");
                }
            },
            "json"
        );
        return false;
    });

    $("#ei-search").keyup(function() {
        if ($(this).val() != "") {
            $.getJSON(
                "autocomplete/ei/" + $(this).val(),
                function(data) {
                    $("#ei-search").autocomplete({
                        source: data
                    });
                }
            );
        }
    });

    $("#ei-form").submit(function(e) {
        e.preventDefault();
        $("#ei-effets").html('<li><img src="/assets/images/wait.gif" height="42" width="42" alt="wait" /></li>');
        $.post(
            "/recherche/ei",
            { "ei-search" : $("#ei-search").val() },
            function(data) {
                $("#ei-effets").empty();
                for (var i = 0; i < data.length; i++) {
                    $("#ei-effets").append("<li>" + data[i] + "</li>");
                }
            },
            "json"
        );
        return false;
    });
});