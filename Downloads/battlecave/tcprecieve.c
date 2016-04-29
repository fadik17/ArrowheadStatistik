#include <SDL.h>
#include <SDL_net.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "tcprecieve.h"
#include "definitions.h"
#include "clearstrings.h"
#include "globalvariables.h"

void handleChat(char TCPTextIn[]);
void handleNames(char TCPTextIn[]);
void handleReady(char TCPTextIn[]);
void handleGameStart(void);
void shiftString(char string[], int steps);

int chatRecieve(Client* client){
    Client* me;
    me = (Client*)client;

    char TCPTextIn[STRINGLENGTH];
    int connectionCheck = 1;

    while(1){
        connectionCheck = SDLNet_TCP_Recv(me->TCPSock, TCPTextIn, STRINGLENGTH);
        if(!connectionCheck){
            printf("Client ID: %d has disconnected!\n", client->id); // *******************************************************************************'
            SDLNet_TCP_Close(me->TCPSock);
        }

        puts(TCPTextIn); //*********************************************************************************************************

        if(TCPTextIn[0] == PREAMBLE_CHAT)
            handleChat(TCPTextIn);
        else if(TCPTextIn[0] == PREAMBLE_PLAYERS)
            handleNames(TCPTextIn);
        else if(TCPTextIn[0] == PREAMBLE_ID)
            client->id = TCPTextIn[1] - 48;
        else if(TCPTextIn[0] == PREAMBLE_READY)
            handleReady(TCPTextIn);
        else if(TCPTextIn[0] == PREAMBLE_GAMESTART)
            handleGameStart();

        if(!isConnected)
            break;
    }
    return 0;
}

void handleChat(char TCPTextIn[]){
    shiftString(TCPTextIn, 1);

    clearTextString(0);
    strcpy(textString[0], textString[1]);
    clearTextString(1);
    strcpy(textString[1], textString[2]);
    clearTextString(2);
    strcpy(textString[2], textString[3]);
    clearTextString(3);
    strcpy(textString[3], textString[4]);
    clearTextString(4);
    strcpy(textString[4], TCPTextIn);
    return;
}

void handleNames(char TCPTextIn[]){
    int id;
    id = TCPTextIn[1] - 48;
    shiftString(TCPTextIn, 2);
    clearPlayerNameString(id);
    strcat(playerName[id], TCPTextIn);
    return;
}

void handleReady(char TCPTextIn[]){
    playerReady[TCPTextIn[1]-48] = (TCPTextIn[2] - 48);
    return;
}

void handleGameStart(void){
    printf("ATTEMPTINT TO START GAME...\n"); //********************************************************************************
    mode = IN_GAME;


    return;
}

void shiftString(char string[], int steps){
    for(int i=0; i < STRINGLENGTH - steps; i++){
        string[i] = string[i+steps];
    }
    return;
}
