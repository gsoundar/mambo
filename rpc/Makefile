# Makefile
# --------------------------------
# by Gokul Soundararajan

include config.mk

all: clean prereq add_server.exe add_client.exe
clean:
prereq: add.h add_svc.c add_clnt.c add_xdr.c

# RPCGEN
add.h: add.x
	${RPCGEN} ${RPCGENFLAGS} add.x
add_svc.c: add.x
	${RPCGEN} ${RPCGENFLAGS} add.x
add_clnt.c: add.x
	${RPCGEN} ${RPCGENFLAGS} add.x
add_xdr.c: add.x
	${RPCGEN} ${RPCGENFLAGS} add.x

# COMPILE
add_xdr.o: add_xdr.c
	${CC} ${CFLAGS} -c add_xdr.c -o add_xdr.o
add_clnt.o: add_clnt.c
	${CC} ${CFLAGS} -c add_clnt.c -o add_clnt.o
add_svc.o: add_svc.c
	${CC} ${CFLAGS} -c add_svc.c -o add_svc.o
add_client.o: add_client.c
	${CC} ${CFLAGS} -c add_client.c -o add_client.o
add_server.o: add_server.c
	${CC} ${CFLAGS} -c add_server.c -o add_server.o

#EXECUTABLES
add_server.exe: add_server.o add_svc.o add_xdr.o
	${CC} ${CFLAGS} add_server.o add_svc.o add_xdr.o -o add_server.exe ${CLIBS} 
add_client.exe: add_client.o add_clnt.o add_xdr.o
	${CC} ${CFLAGS} add_client.o add_clnt.o add_xdr.o -o add_client.exe ${CLIBS} 

clean:
	rm -f *.exe *.o *.*~ *~

#rm -f add.h add_svc.c add_clnt.c add_xdr.c

