//This is just a test
var guiLobbyUsernames = ["null","null","null","null"];
//var guiLobbyUsernames = ["Lord Spooky Bones","Azias","[Insert name here]","I don't know what i can add."];
var guiLobbySelectedCos = ["null","null","null","null"];
var guiLobbyReadyList = "2222";

var guiCommonChatMessages = [];

var addChatMessage = function(msg) {
	guiCommonChatMessages.push(msg);
};

var clearChat = function clearChat() {
	guiCommonChatMessages.length = 0;
};

var getLobbyUsernames = function() {
	var result = '';
	for (i = 0; i < guiLobbyUsernames.length; i++) { 
	    result += guiLobbyUsernames[i] + ".";
	}
	result = result.substring(0, result.length - 1);
	//print(result);
    return result;
};

var getLobbyReadyList = function() {
    return guiLobbyReadyList;
};

var getLobbyCOs = function() {
	var result = '';
	for (i = 0; i < guiLobbySelectedCos.length; i++) { 
	    result += guiLobbySelectedCos[i] + ".";
	}
	result = result.substring(0, result.length - 1);
    return result;
};
