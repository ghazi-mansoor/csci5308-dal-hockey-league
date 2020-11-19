package com.groupten.statemachinenew;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.statemachinenew.states.IState;
import com.groupten.statemachinenew.states.ReadyState;

public class StateMachine {
    IState state;

    public StateMachine(){
        this.state = new ReadyState(this);
    }

    public void setState(IState state){
        this.state = state;
    }

    public void doAction(){
        this.state.doAction();
    }

    public void retry(){
        IConsole console = Injector.instance().getConsoleObject();
        String choice = console.readLine().toLowerCase();
        console.printLine("\nDo you want to Retry? (y/n)");
        if(choice.equals("y")){
            doAction();
        }else if(choice.equals("n")){
            end();
        }else{
            console.printLine("ERROR: Invalid Input.");
            retry();
        }
    }

    private void end() {
        IConsole console = Injector.instance().getConsoleObject();
        console.printLine("Good Bye!");
        System.exit(0);
    }
}
