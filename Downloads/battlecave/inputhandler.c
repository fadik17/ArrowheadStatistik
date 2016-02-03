#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <SDL.h>
#include "inputhandler.h"
#include "definitions.h"
#include "clearstrings.h"
#include "globalvariables.h"
#include "tcprecieve.h"

static void checkMouseMode0(SDL_Event *event, SDL_Point *currentMouseLocation, SDL_Rect buttonPlacement[], int *select, int *mode, int modeMaxButtons[], bool *match, bool *quit);
static void checkMouseMode1(SDL_Event *event, SDL_Point *currentMouseLocation, SDL_Rect buttonPlacement[], int *select, int *mode, int modeMaxButtons[], bool *match);
static void checkMouseMode3(SDL_Event *event, SDL_Point *currentMouseLocation, SDL_Rect buttonPlacement[], int *select, int *mode, int modeMaxButtons[], bool *match, int *keyboardMode);
static void checkMouseMode5(SDL_Event *event, SDL_Point *currentMouseLocation, SDL_Rect buttonPlacement[], int *select, int *mode, int modeMaxButtons[], bool *match, int *keyboardMode);
static bool mouseOnButton(SDL_Point *currentMouseLocation, SDL_Rect buttonPlacement[], int *i);

void handleBackspace(int id);
void addCharToString(int id, int maxLen, SDL_Event *event);
void joinLobby(int *mode);
int resolveIPPortFromStrings(void);

//**************************************************************************
//                          Mouse Handling                                 *
//**************************************************************************

void checkMouse(SDL_Event *event, SDL_Rect buttonPlacement[], int *select, int *mode, int modeMaxButtons[], int *keyboardMode, bool *quit){
    SDL_Point currentMouseLocation;
    SDL_GetMouseState(&currentMouseLocation.x, &currentMouseLocation.y);

    bool match = false;

    if(*mode == STARTUP)
        checkMouseMode0(event, &currentMouseLocation, buttonPlacement, select, mode, modeMaxButtons, &match, quit);
    else if(*mode == FIND_SERVERS)
        checkMouseMode1(event, &currentMouseLocation, buttonPlacement, select, mode, modeMaxButtons, &match);
    else if(*mode == LOBBY)
        checkMouseMode3(event, &currentMouseLocation, buttonPlacement, select, mode, modeMaxButtons, &match, keyboardMode);
    else if(*mode == JOIN_CUSTOM)
        checkMouseMode5(event, &currentMouseLocation, buttonPlacement, select, mode, modeMaxButtons, &match, keyboardMode);


    if((*mode == STARTUP || *mode == FIND_SERVERS) && !match)
        *select = -1;

    return;
}

//**************************************************************************
//*                   MODE 0 (Startup Screen)                              *
//**************************************************************************
static void checkMouseMode0(SDL_Event *event, SDL_Point *currentMouseLocation, SDL_Rect buttonPlacement[], int *select, int *mode, int modeMaxButtons[], bool *match, bool *quit){
    for(int i=0; i < modeMaxButtons[0]; i++){
        if(mouseOnButton(currentMouseLocation, buttonPlacement, &i)){ // Is the mouse on button 'i' ?
            *select = i;
            *match = true;
            if(event->type == SDL_MOUSEBUTTONDOWN){
                if(i == 2){     // EXIT
                    *quit = true;
                }
                else if(i == 1) // OPTIONS
                    printf("OPTIONS\n");
                else{           // FIND SERVERS
                    *mode = FIND_SERVERS;
                }
            }
        }
    }
    return;
}
//**************************************************************************
//*                   MODE 1 (Find Servers Screen)                         *
//**************************************************************************
static void checkMouseMode1(SDL_Event *event, SDL_Point *currentMouseLocation, SDL_Rect buttonPlacement[], int *select, int *mode, int modeMaxButtons[], bool *match){
    for(int i=0; i < modeMaxButtons[1]; i++){
        if(mouseOnButton(currentMouseLocation, buttonPlacement, &i)){ // Is the mouse on button 'i' ?
            *select = i;
            *match = true;
            if(event->type == SDL_MOUSEBUTTONDOWN){
                if(i == 2){         // BACK
                    *mode = STARTUP;
                }
                else if(i == 1){     // JOIN CUSTOM SERVER
                    *mode = JOIN_CUSTOM;
                    *select = -1;
                }
                else{               // JOIN DEFAULT SERVER
                    printf("JOIN DEFAULT SERVER\n");
                }
            }
        }
    }
    return;
}

//**************************************************************************
//*                   MODE 3 Lobby Screen                                  *
//**************************************************************************
static void checkMouseMode3(SDL_Event *event, SDL_Point *currentMouseLocation, SDL_Rect buttonPlacement[], int *select, int *mode, int modeMaxButtons[], bool *match, int *keyboardMode){
    for(int i=0; i < modeMaxButtons[3]; i++){
        if(mouseOnButton(currentMouseLocation, buttonPlacement, &i)){ // Is the mouse on button 'i' ?
            if(event->type == SDL_MOUSEBUTTONDOWN){
                *keyboardMode = ENTERING_TEXT;

                if(i == 2){                 // Ready
                    //playerReady = !playerReady;
                    SDLNet_TCP_Send(client.TCPSock, "#", strlen("#"));

                }
                else if(i == 1){            // Leave
                    isConnected = false;
                    SDLNet_TCP_Send(client.TCPSock, "-", strlen("-"));
                    SDLNet_TCP_Close(client.TCPSock);
                    SDLNet_UDP_Close(client.UDPRecvSock);
                    SDLNet_UDP_Close(client.UDPSendSock);

                    for(int i=0; i < MAX_PLAYERS; i++)
                        playerReady[i] = 0;

                    clearTextStrings(6);
                    printf("LEFT; '-' sent to server, socket closed, ready statuses cleared, textstrings cleared, mode changed\n"); //*****************************************
                    *mode = 5;
                }                           // (ELSE: Enter Chat Message Window Pressed)
            }
        }
    }
}
//**************************************************************************
//*                   MODE 5 (Join Custom Server Screen)                   *
//**************************************************************************
static void checkMouseMode5(SDL_Event *event, SDL_Point *currentMouseLocation, SDL_Rect buttonPlacement[], int *select, int *mode, int modeMaxButtons[], bool *match, int *keyboardMode){
    for(int i=0; i < modeMaxButtons[5]; i++){
        if(mouseOnButton(currentMouseLocation, buttonPlacement, &i)){ // Is the mouse on button 'i' ?
            if(event->type == SDL_MOUSEBUTTONDOWN){
                *keyboardMode = NONE;

                if(i == 4){         // GO!
                    joinLobby(mode);
                }
                else if(i == 3){    // Close
                    *mode = FIND_SERVERS;
                }
                else{    // Enter IP _OR_ Port _OR_ Name Field
                    *select = i;
                    *keyboardMode = ENTERING_TEXT;
                    *match = true;
                }
            }
        }
    }
    return;
}


//**************************************************************************


//**************************************************************************
//                          KeyPress Handling                              *
//**************************************************************************

void checkKeypress(SDL_Event *event, int *mode, int *keyboardMode, int *select){
//    if(*keyboardMode < 0)
//        return;

    if(*mode == JOIN_CUSTOM){
        if(event->key.keysym.sym == SDLK_TAB){                      // Tab will change the window that the user is currently entering text to
            (*select)++;
            if(*select > 2)
                *select = 0;
        }
        else if(event->key.keysym.sym == SDLK_RETURN){
            joinLobby(mode);
        }


        else{
            if(*select == ENTERING_IP){
                if(event->key.keysym.sym == SDLK_BACKSPACE)
                    handleBackspace(ENTERING_IP);
                else
                    addCharToString(ENTERING_IP, MAX_IP_LENGTH, event);
            }

            else if(*select == ENTERING_PORT){
                if(event->key.keysym.sym == SDLK_BACKSPACE)
                    handleBackspace(ENTERING_PORT);
                else
                    addCharToString(ENTERING_PORT, MAX_PORT_LENGTH, event);
            }

            else if(*select == ENTERING_NAME){
                if(event->key.keysym.sym == SDLK_BACKSPACE)
                    handleBackspace(ENTERING_NAME);
                else
                    addCharToString(ENTERING_NAME, MAX_NAME_LENGTH, event);

            }
        }
    }

    if(*mode == LOBBY){ // If we're in a lobby

        if(event->key.keysym.sym == SDLK_BACKSPACE)
            handleBackspace(PLAYER_MESSAGE_WRITE);
        else if(event->key.keysym.sym == SDLK_RETURN){
            SDLNet_TCP_Send(client.TCPSock, textString[PLAYER_MESSAGE_WRITE], STRINGLENGTH);
            clearTextString(PLAYER_MESSAGE_WRITE);
        }
        else
            addCharToString(PLAYER_MESSAGE_WRITE, 40, event);
    }


    return;
}

void handleBackspace(int id){
    if(textStringCurrent[id] > 0){
        textStringCurrent[id]--;
        textString[id][textStringCurrent[id]] = '\0';
    }
    return;
}

void addCharToString(int id, int maxLen, SDL_Event *event){
    if(textStringCurrent[id] < maxLen){
        textString[id][textStringCurrent[id]] = event->key.keysym.sym;
        textStringCurrent[id]++;
    }
    return;
}


static bool mouseOnButton(SDL_Point *currentMouseLocation, SDL_Rect buttonPlacement[], int *i){
    if(currentMouseLocation->x >= buttonPlacement[*i].x && currentMouseLocation->x <= (buttonPlacement[*i].x + buttonPlacement[*i].w) &&
       currentMouseLocation->y >= buttonPlacement[*i].y && currentMouseLocation->y <= (buttonPlacement[*i].y + buttonPlacement[*i].h))
        return true;
    else
        return false;
}

void joinLobby(int *mode){
    printf("DEBUG: JOINING LOBBY... REMOVE THIS LATER (inputhandler.c:242)\n");
    bool success;
    success = resolveIPPortFromStrings();
    if(!success)
        return;

    client.TCPSock = SDLNet_TCP_Open(&ip);
    if(client.TCPSock == NULL){
        printf("TCP Open Failure\n");
        //exit(EXIT_FAILURE);
        return;
    }

    SDLNet_TCP_Send(client.TCPSock, textString[ENTERING_NAME], MAX_NAME_LENGTH);

    client.UDPRecvSock = SDLNet_UDP_Open(0);
    client.UDPSendSock = SDLNet_UDP_Open(0);
    if(client.UDPRecvSock == NULL || client.UDPSendSock == NULL){
        printf("Opening of UDP sockets failed!\n");
        exit(EXIT_FAILURE);
    }
//
//    // Send the clients ports for UDP recieve and UDP send
//    SDLNet_TCP_Send(client.TCPSock, 1234, sizeof(int)); printf("1\n");
//    //SDLNet_TCP_Send(client.TCPSock, &(SDLNet_UDP_GetPeerAddress(client.UDPSendSock,-1)->port),sizeof(Uint16));
//    SDLNet_TCP_Recv(client.TCPSock, &(client.ServerRecvUDPPort), sizeof(int));printf("2\n");
//    printf("sENT: %x\nRecieved port: %x\n", (SDLNet_UDP_GetPeerAddress(client.UDPRecvSock,-1))->port, client.ServerRecvUDPPort);printf("3\n");

    printf("CONNECTED\n"); exit(0);

    chatRecv = SDL_CreateThread(chatRecieve, "BC ChatRecv", &client);
    SDL_DetachThread(chatRecv);
    isConnected = true;
    //*******************************************************************************************
    *mode = LOBBY;
    clearTextStrings(6);
    return;
}

int resolveIPPortFromStrings(void){
    int test;
    Uint16 temp = atoi(textString[1]);

    test = SDLNet_ResolveHost(&ip, textString[0], temp);
    if(test < 0){
        printf("IP ADDRESS RESOLVE FAILED\n");  // **************************************************************************************
        return 0;
    }
    return 1;
}
