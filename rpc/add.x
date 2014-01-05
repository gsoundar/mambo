/*
 * ADD - RPC
 * ---------
 * by Gokul Soundararajan
 * 
 * Copied from http://www.pk.org/rutgers/hw/rpc/index.html 
 *
 */

struct intpair {
    int a;
    int b;
};

program ADD_PROG {
    version ADD_VERS {
        int ADD(intpair) = 1;
    } = 1;
} = 0x23451111;