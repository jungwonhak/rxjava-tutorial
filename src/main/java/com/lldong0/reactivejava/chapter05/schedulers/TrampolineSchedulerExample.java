package com.lldong0.reactivejava.chapter05.schedulers;

import com.lldong0.reactivejava.common.CommonUtils;
import com.lldong0.reactivejava.common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TrampolineSchedulerExample {

	/**
	 * 순서를 보장해주는 스케줄러
	 */
	public void run() {
		String[] orgs = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(orgs);
		
		//Subscription #1 
		source.subscribeOn(Schedulers.trampoline())
				.map(data -> "<<" + data + ">>")
				.subscribe(Log::i);
		
		//Subscription #2 
		source.subscribeOn(Schedulers.trampoline())
				.map(data -> "##" + data + "##")
				.subscribe(Log::i);
		CommonUtils.sleep(500);
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) { 
		TrampolineSchedulerExample demo = new TrampolineSchedulerExample();
		demo.run();
	}
}
