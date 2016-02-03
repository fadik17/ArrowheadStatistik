#include "globalvariables.h"

Client client;
GameBackground gameBackground;
Ship ship[MAX_PLAYERS];
Bullet bullet[MAX_BULLETS];

int mode;                               // Modes as specified in "definitions.h"

bool isConnected;                       // True when connected to a lobby or game

char textString[6][STRINGLENGTH];       // Used for both chat messages and for example IP addresses and ports
int textStringColor[6];                 // Decides clors for text messages
int textStringCurrent[6];               // Which index in the string are we currently addressing?

char playerName[MAX_PLAYERS][MAX_NAME_LENGTH];
bool playerReady[8];
char playerReadyStr[2];
SDL_Rect readyIcon;
//char name[MAX_NAME_LENGTH]; *********************************************************************

SDL_Thread* chatRecv;
IPaddress ip;                           // Contains the information (host + port) for the server to connect to
