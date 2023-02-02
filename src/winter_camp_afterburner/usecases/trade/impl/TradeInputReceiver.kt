package winter_camp_afterburner.usecases.trade.impl


interface TradeInputReceiver : CancelInteractor.InputReceiver,
    TradeInteractor.InputReceiver,
    FinalClosingInteractor.InputReceiver