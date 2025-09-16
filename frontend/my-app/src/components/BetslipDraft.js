import { useState } from "react";
import '../styles/BetslipDraft.css';

const BetslipDraft = () => {
  const [stake, setStake] = useState(50);

  return (
    <div className="betslip-draft-container">
      <div className="betslip-draft">
        <div className="betslip-draft-top-part">
          <div className="betslip-draft-match-container">
            {/* List of selected matches */}
          </div>
          <p>Kurz: </p>
        </div>

        <div className="betslip-draft-bottom-part">
          <div className="betslip-draft-stake-section">
            <div className="betslip-draft-stake-input">
              <span>$</span>
              <input
                type="number"
                value={stake}
                onChange={(e) => setStake(Number(e.target.value))}
              />
            </div>

            <div>
              <label htmlFor="stake">
                Stake Amount: <span id="stake-value">{stake}</span>
              </label>
              <input
                type="range"
                id="stake"
                name="stake"
                min="0"
                max="100"
                value={stake}
                step="1"
                onChange={(e) => setStake(Number(e.target.value))}
              />
            </div>
          </div>

          <div className="betslip-draft-acception-part">
            <button>Pridať tiket</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BetslipDraft;
