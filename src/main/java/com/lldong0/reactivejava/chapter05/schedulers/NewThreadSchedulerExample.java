package com.lldong0.reactivejava.chapter05.schedulers;

import static com.lldong0.reactivejava.common.Shape.BLUE;
import static com.lldong0.reactivejava.common.Shape.GREEN;
import static com.lldong0.reactivejava.common.Shape.RED;

import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewThreadSchedulerExample {
	public void basic() { 
		String[] orgs = {RED, GREEN, BLUE};
		Observable.fromArray(orgs)
			.doOnNext(data -> Log.v("Original data : " + data))
			.map(data -> "<<" + data + ">>")
			.subscribeOn(Schedulers.newThread())
			.subscribe(Log::i);
//		CommonUtils.sleep(500);
		
		Observable.fromArray(orgs)
			.doOnNext(data -> Log.v("Original data : " + data))
			.map(data -> "##" + data + "##")
			.subscribeOn(Schedulers.newThread())
			.subscribe(Log::i);		
//		CommonUtils.sleep(500);
	}
	
	public static void main(String[] args) { 
		NewThreadSchedulerExample demo = new NewThreadSchedulerExample();
		demo.basic();
	}
}
