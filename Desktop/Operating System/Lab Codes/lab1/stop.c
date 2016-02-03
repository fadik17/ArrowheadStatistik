#include <signal.h>
#include <stdio.h>
#include <fcntl.h>
#define PID_PATH "/tmp/pid.txt"

int main(){
	FILE* fp;
	int c, i;
	fp = open(PID_PATH, "r");
	while(1){
	
		c = fgetc(fp);
		if(feof(fp)){
			break;
		}
		i = atoi(c);
		kill(i,SIGKILL);
		
	}
	fclose(fp);
	return 0;
	
}
//... 

//fscanf 

//kill 
