package com.gsis.dao;

/*
 * @author : Johne Altamera
 */

class ConnectionReaper extends Thread {

    private JDCConnectionPool pool;
    private final long delay=300000;

    ConnectionReaper(JDCConnectionPool pool) {
        this.pool=pool;
    }

    public void run() {
        while(true) {
           try {
              sleep(delay);
           } catch( InterruptedException e) { }
           pool.reapConnections();
        }
    }
}

