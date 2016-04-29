/* server.c -- the  server for lab1; demostrates unix sockets. 
 * Expended from echos.c.
 */

#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <signal.h>
#include <wait.h>
#include <unistd.h> // For dup2()
#include <fcntl.h>

/* Bind UNIX domain socket to pathname in the /tmp directory which is normally writable.
 The socket should be bind()ed to absolute pathname in more secure directory in the future. */
#define SOCK_PATH "/tmp/echo_socket"
#define EXIT_SUCCESS 0
#define EXIT_FAILURE 1
#define CMD_SIZE 2000
#define PID_PATH	"/tmp/pid.txt"

/* Daemon prototype from www.ics.uzh.ch */
static void daemonize(void)
{
    pid_t pid, sid;

    if ( getppid() == 1 ) return; 	// A daemon allready exists.
    pid = fork();		    	// Fork the parent process.
    if (pid < 0) {
        exit(EXIT_FAILURE);		// Fail to spawn child process.
    }
    if (pid > 0) {
        exit(EXIT_SUCCESS);		// Success to spawn child process.
    }
    umask(0);				// Set a mask of permission bits that are always turned off

    sid = setsid();			// Child process gets a new session ID: SID.
    if (sid < 0) {
        exit(EXIT_FAILURE);		// Fail to get a new SID.
    }
	
    char* stream = PID_PATH;
    char no[4];
    int fd;
    fd = open(stream,O_RDWR|O_CREAT, 0600);
    sprintf(no, "%d\n", sid);
    write(fd, no, strlen(no));
    
	
    if ((chdir("/")) < 0) {
        exit(EXIT_FAILURE);		// Change the current working directory to prevent
					// the current directory to be locked.
    }

	/* Redirect standard files to /dev/null. */
    freopen( "/dev/null", "r", stdin);
    freopen( "/dev/null", "w", stdout);
    freopen( "/dev/null", "w", stderr);

    closelog();				// Disable the connection with the log. "syslog" API.
}

/*Loop inside the SIGCHLD handler, repeatedly calling waitpid() with the WNOHANG flag
 until there are no more dead children to be reaped. */
void sigchld_handler(int s)
{
    while(waitpid(-1, NULL, WNOHANG) > 0);
}

/* Set a label to tell the client the end of data sream. */


int main(void)
{
    int s, s2, t, len, i;
    struct sockaddr_un local, remote;
    char str[CMD_SIZE]= {'\0'}, dir[CMD_SIZE-3]={'\0'};
    
    
    printf("\nDaemon is activated...\nPlease start a client...\n\n");
    daemonize();

    // ta processid -> fil            /tmp/pid
/*	char* id = PID_PATH;
	char no[4];
	int fd;
	fd = open(id, O_RDWR|O_CREAT,0600);
	sprintf(no, "%d\n", sid);
	write(fd, no, strlen(no));

*/
/*	pid_t pid;
	pid = getpid();
	FILE* fp;
	fp = open(PID_PATH, "w+");
	fprintf(fp, "%d", pid);
	close(fp);

*/

    /* Creat a new socket. */
    if ((s = socket(AF_UNIX, SOCK_STREAM, 0)) == -1)
    {
        perror("socket");
        exit(1);
    }

    struct sigaction sa;
    sa.sa_handler = sigchld_handler;	// Specifies the address of the signal handler. 
    sigemptyset(&sa.sa_mask);		// Initializes a signal set.
    sa.sa_flags = SA_RESTART;		// Automatically restart system calls interrupted by this
					// signal handler.

    if (sigaction(SIGCHLD, &sa, NULL) == -1)
    {
        perror("sigaction");
        exit(1);
    }

/* Apply UNIX domain address. */
    local.sun_family = AF_UNIX;		// Alternatively PF_UNIX.
    strcpy(local.sun_path, SOCK_PATH);
    unlink(local.sun_path);
/* Specifies the size of the address structure. */
    len = strlen(local.sun_path) + sizeof(local.sun_family);
/* Binds the socket to the address known by client. */
    if (bind(s, (struct sockaddr *)&local, len) == -1)
    {
        perror("bind");
        exit(1);
    }

/* Allow a stream socket to accept incoming connection from other socket. */
    if (listen(s, 5) == -1)
    {
        perror("listen");
        exit(1);
    }
    int done, n;
    printf("\nWaiting for a connection...\n");
    t = sizeof(remote);

/* Handle client connections iteratively. */
    for(;;)
    {

/* Accept a connection from a peer application on a listening stream socket, and optionally 
returns the address of the peer socket. */
        if ((s2 = accept(s, (struct sockaddr *)&remote, &t)) == -1) 
        {
            perror("accept");
            exit(1);
        }
        printf("Connected.\n\n");
        done = 0;
        
/* Child process is working here. */
        if(fork()==0) 
        { 
/* Redirect the STD streams to the socket in order to send data to client. */            
            close(1);
            dup2(s2, 1);
            close(2); 
	    dup2(s2, 2);

            do
            {
            
/* Receive command from client. 
recv() returns number of bytes received, 0 on EOF, or -1 on error. */
                n = recv(s2, str, CMD_SIZE, 0); 
                if (n <= 0)
                {
                    if (n < 0) perror("recv");
                    done = 1;
                }

/* When command 'cd' is received. */
                if(str[0]=='c'&& str[1]=='d'){ 

/* Copy the rest of command except the first 3 characters 'c', 'd', ' '
 to the dir[] (directory). */
                    strncpy(dir, &str[3], strlen(str)-3);
                    dir[strlen(str)-4]='\0';
                    
/* Change working directory.
 chdir() returns 0 on success, -1 on error. */
                    if(chdir(dir)==-1){
			perror("Cannot change directory");
			}
                }

                else{
/* Child process run commands other than 'cd ..'. */
                        if(fork()==0) 
                        {
                            execlp("/bin/sh","sh", "-c", str, NULL);

                        }
			wait(0);	
                    }
 
                    wait(0);

/* Flush str[] */                    
		    for(i=0;i<sizeof(str);i++){ 
                        str[i]='\0';
			}

                    
/* Set a label to tell the client the end of data sream. */
			switch (fork()){
		            case -1:
			    perror("Cannot fork()");
		            exit(1);
				 
			    case 0:
		            execlp("/bin/echo", "echo", "END", NULL);
		            perror("exec");
		            exit(1);
			}
			wait(0);


            }while (!done);
            
	close(s2);
	exit(0);
        }
	
    }
    
    close(s2);
    close(s);
    return 0;
} 


