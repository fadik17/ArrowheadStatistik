#include <stdio.h>
#include <SDL.h>
#include <SDL_ttf.h>
#include "settext.h"
#include "definitions.h"
#include "globalvariables.h"

SDL_Surface* gTempTextMessage;
SDL_Texture* mText = NULL;

static SDL_Color White = {255, 255, 255};
static SDL_Color Yellow = {255, 255, 150};
//static SDL_Color Black = {0, 0, 0};
//static SDL_Color Green = {100, 255, 100};
//static SDL_Color Red = {255, 100, 100};
static SDL_Color Teal = {45, 198, 250};

void setTextMode0(SDL_Rect *textPlacement, SDL_Renderer* gRenderer, int *select);
void setTextMode1(SDL_Rect *textPlacement, SDL_Renderer* gRenderer, int *select);
void setTextMode3(SDL_Rect *textPlacement, SDL_Renderer* gRenderer);
void setTextMode5(SDL_Rect *textPlacement, SDL_Renderer* gRenderer);
void renderText(SDL_Rect *textPlacement, SDL_Renderer* gRenderer);

void setText(int *mode, SDL_Renderer* gRenderer, int *select){
    static SDL_Rect textPlacement;

    if(*mode == STARTUP)
        setTextMode0(&textPlacement, gRenderer, select);
    else if(*mode == FIND_SERVERS)
        setTextMode1(&textPlacement, gRenderer, select);
    else if(*mode == LOBBY)
        setTextMode3(&textPlacement, gRenderer);
    else if(*mode == JOIN_CUSTOM)
        setTextMode5(&textPlacement, gRenderer);

    return;
}

void setTextMode0(SDL_Rect *textPlacement, SDL_Renderer* gRenderer, int *select){
    TTF_Font* font = TTF_OpenFont("resources/fonts/arial.ttf", 40);

    textPlacement->x = SCREENWIDTH/6;
    textPlacement->y = 4*(SCREENHEIGHT/6);
    if(*select == 0)
        gTempTextMessage = TTF_RenderText_Solid(font, "Find Servers", Yellow);
    else
        gTempTextMessage = TTF_RenderText_Solid(font, "Find Servers", White);
    renderText(textPlacement, gRenderer);

    textPlacement->y = 4*(SCREENHEIGHT/6) + 50;
    if(*select == 1)
        gTempTextMessage = TTF_RenderText_Solid(font, "Options", Yellow);
    else
        gTempTextMessage = TTF_RenderText_Solid(font, "Options", White);
    renderText(textPlacement, gRenderer);

    textPlacement->y = 4*(SCREENHEIGHT/6) + 100;
    if(*select == 2)
        gTempTextMessage = TTF_RenderText_Solid(font, "Exit", Yellow);
    else
        gTempTextMessage = TTF_RenderText_Solid(font, "Exit", White);
    renderText(textPlacement, gRenderer);

    TTF_CloseFont(font);
    return;
}

void setTextMode1(SDL_Rect *textPlacement, SDL_Renderer* gRenderer, int *select){
    TTF_Font* font = TTF_OpenFont("resources/fonts/arial.ttf", 40);

    textPlacement->x = SCREENWIDTH/6;
    textPlacement->y = 4*(SCREENHEIGHT/6);
    if(*select == 0)
        gTempTextMessage = TTF_RenderText_Solid(font, "Join Default Server", Yellow);
    else
        gTempTextMessage = TTF_RenderText_Solid(font, "Join Default Server", White);
    renderText(textPlacement, gRenderer);

    textPlacement->y = 4*(SCREENHEIGHT/6) + 50;
    if(*select == 1)
        gTempTextMessage = TTF_RenderText_Solid(font, "Join Custom Server", Yellow);
    else
        gTempTextMessage = TTF_RenderText_Solid(font, "Join Custom Server", White);
    renderText(textPlacement, gRenderer);

    textPlacement->y = 4*(SCREENHEIGHT/6) + 100;
    if(*select == 2)
        gTempTextMessage = TTF_RenderText_Solid(font, "Back", Yellow);
    else
        gTempTextMessage = TTF_RenderText_Solid(font, "Back", White);
    renderText(textPlacement, gRenderer);

    TTF_CloseFont(font);
}

void setTextMode3(SDL_Rect *textPlacement, SDL_Renderer* gRenderer){
    TTF_Font* font = TTF_OpenFont("resources/fonts/arial.ttf", 20);

    // Chat messages (0 upper, 4 lower)
    textPlacement->x = 545;
    textPlacement->y = 411;
    for(int i=0; i < 5; i ++){
        gTempTextMessage = TTF_RenderText_Solid(font, textString[i], White);
        renderText(textPlacement, gRenderer);
        textPlacement->y += 33;
    }

    // Chat message that the user is typing
    textPlacement->y = 590;
    gTempTextMessage = TTF_RenderText_Solid(font, textString[PLAYER_MESSAGE_WRITE], White);
    renderText(textPlacement, gRenderer);

    TTF_CloseFont(font);
    font = TTF_OpenFont("resources/fonts/arial.ttf", 25);
    // Names of connected players
    textPlacement->x = 190;
    textPlacement->y = 120;

    for(int i=0; i < MAX_PLAYERS; i++){
        gTempTextMessage = TTF_RenderText_Solid(font, playerName[i], Teal);
        renderText(textPlacement, gRenderer);
        textPlacement->y += 64;
    }

    TTF_CloseFont(font);
    return;
}

void setTextMode5(SDL_Rect *textPlacement, SDL_Renderer* gRenderer){
    TTF_Font* font = TTF_OpenFont("resources/fonts/arial.ttf", 30);

    textPlacement->x = SCREENWIDTH/2 - 130;
    textPlacement->y = SCREENHEIGHT/2 - 57;
    gTempTextMessage = TTF_RenderText_Solid(font, textString[ENTERING_IP], Teal);
    renderText(textPlacement, gRenderer);

    textPlacement->y = SCREENHEIGHT/2 + 18;
    gTempTextMessage = TTF_RenderText_Solid(font, textString[ENTERING_PORT], Teal);
    renderText(textPlacement, gRenderer);

    textPlacement->y = SCREENHEIGHT/2 + 93;
    gTempTextMessage = TTF_RenderText_Solid(font, textString[ENTERING_NAME], Teal);
    renderText(textPlacement, gRenderer);

    TTF_CloseFont(font);
    return;
}

void renderText(SDL_Rect *textPlacement, SDL_Renderer* gRenderer){
    mText = SDL_CreateTextureFromSurface(gRenderer, gTempTextMessage);
    SDL_QueryTexture(mText, NULL, NULL, &textPlacement->w, &textPlacement->h);
    SDL_RenderCopy(gRenderer, mText, NULL, textPlacement);

    SDL_FreeSurface(gTempTextMessage);
    SDL_DestroyTexture(mText);
    return;
}
