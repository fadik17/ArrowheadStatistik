/* Client.c -- client program for lab1, os.
 * Expended from echoc.c.
 */
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>

#define SOCK_PATH "/tmp/echo_socket"
#define CMD_SIZE 2000


int main(void)
{

  int s, t, len;
  struct sockaddr_un remote;
  char str[CMD_SIZE] = {'\0'};

/* Creat socket. */
  if ((s = socket(AF_UNIX, SOCK_STREAM, 0)) == -1) {
    perror("socket");
    exit(1);
  }

  printf("\nTrying to connect...\n");

/* Construct address and make the connection. */
  remote.sun_family = AF_UNIX;
  strcpy(remote.sun_path, SOCK_PATH);
  len = strlen(remote.sun_path) + sizeof(remote.sun_family);

/* Establish a connection with another socket(server's end)
 connect() returns 0 on success, or -1 on error. */  
if (connect(s, (struct sockaddr *)&remote, len) == -1) {
    perror("connect");
    exit(1);
  }

    printf("\nConnected.\n");

/* Copy STDIN to socket */
  while(printf("Input command here > "), fgets(str, CMD_SIZE, stdin), !feof(stdin)) {
    /* Send datagrams on socket. */
      if (send(s, str, strlen(str), 0) == -1) {
      perror("send");
      exit(1);
    }

    int done;
    done=0;
    do {
        if ((t=recv(s, str, CMD_SIZE, 0)) > 0) {  
          str[t] = '\0';

	/* strstr() returns 0 if no match is found. */
          if(strstr(str, "END"))
            {
                printf("---END---\n");
                done=1;
            }
            else {
              printf("%s", str);
            }

        } else {
          if (t < 0) perror("recv");
          else printf("Server closed connection\n");
          exit(1);

            }
      } while(!done);

    }

    close(s);

    return 0;
}
