#include <SDL.h>
#include "setwindows.h"
#include "definitions.h"

void setWindows(SDL_Rect windowPlacement[]){
    windowPlacement[0].x = SCREENWIDTH/2 - 150;        // 0 = IP / Port Window
    windowPlacement[0].y = SCREENHEIGHT/2 - 100;
    windowPlacement[0].h = 275;
    windowPlacement[0].w = 300;
    windowPlacement[1].x = 90;                         // 1 = Lobby Window
    windowPlacement[1].y = 50;
    windowPlacement[1].h = 620;
    windowPlacement[1].w = 1100;
    return;
}
