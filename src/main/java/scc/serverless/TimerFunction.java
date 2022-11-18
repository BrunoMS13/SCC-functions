package scc.serverless;

import com.azure.cosmos.util.CosmosPagedIterable;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import scc.cache.CosmosDBLayer;
import scc.cache.RedisLayer;
import scc.dataclasses.AuctionDAO;
import scc.dataclasses.Bid;

import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * Azure Functions with Timer Trigger.
 */
public class TimerFunction {
    @FunctionName("auction-closer")
    public void cosmosFunction( @TimerTrigger(name = "periodicSetTime",
    								schedule = "0 */5 * * * *")
    				String timerInfo,
    				ExecutionContext context) {

		CosmosDBLayer db = CosmosDBLayer.getInstance();
		RedisLayer rl = RedisLayer.getInstance();

		CosmosPagedIterable<AuctionDAO> temp = db.getAuctions();
		Iterator<AuctionDAO> it = temp.iterator();

		Logger.getGlobal().info("------ Running Through Auctions ------");
		while (it.hasNext()) {
			AuctionDAO a = it.next();
			if (a.getEndingTime().getTime() < System.currentTimeMillis() && !a.getStatus().equals("CLOSED")) {
				Logger.getGlobal().info("Auction about to be closed: " + a);
				a.setStatus("CLOSED");
				Collection<Bid> bids = a.getBids().values();
				float winnerBid = 0;
				Logger.getGlobal().info("------ Running Through Auction Bids ------");
				try {
					for (Bid bid : bids) {
						float curBid = bid.getBidValue();
						if (curBid > winnerBid) {
							winnerBid = bid.getBidValue();
							a.setWinnerId(bid.getUserId());
						}
					}
					Logger.getGlobal().info("Winner= " + a.getWinnerId());
				} catch (Exception e) {}
				db.updateAuction(a);
				rl.updateAuction(a);
			}
			Logger.getGlobal().info("\n");
		}
    }
}
