<script lang="ts">
  const url = "http://api.michiko.praise.com/praise/";
  let mihikoImg = "images/michiko.jpg";
  let name = "";
  let praised = "";
  let showAddModal = false;
  let addingPraiseWord = "";

  const addPraiseWord = async () => {
    if (!addingPraiseWord) {
      return;
    }
    await fetch(baseUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ word: addingPraiseWord }),
    });
    showAddModal = false;
    addingPraiseWord = "";
  };

  const randomPraise = async () => {
    if (!name) {
      return;
    }
    const res = await fetch(baseUrl + "random");
    const json = await res.json();
    praised = json.word;
  };

  const handleNameInputPress = async (e: KeyboardEvent) => {
    if (e.key === "Enter") {
      await randomPraise();
    }
  };

  const handleNameSubmit = async () => {
    await randomPraise();
  };

  const handleAddWordInputPress = async (e: KeyboardEvent) => {
    if (e.key === "Enter") {
      await addPraiseWord();
    }
  };

  const handleAddWordSubmit = async () => {
    await addPraiseWord();
  };

  const handleAddButtonClicked = () => {
    showAddModal = true;
  };
</script>

<main>
  <div class="main-container">
    <h1>みちこがあなたを褒め殺しにするよ！</h1>
    <div class="main-image-container">
      <img src={mihikoImg} alt="みちこさま" />
    </div>
    <div class="input-container">
      <input
        bind:value={name}
        placeholder="名前を入力してね"
        on:keypress={handleNameInputPress}
      />
      <button on:click={handleNameSubmit}>褒められる！</button>
    </div>
    <div class="praised">
      {#if praised}
        <p>こんなふうに褒めてくれたよ！</p>
        <p>{praised}{name}！</p>
      {/if}
    </div>
    <button on:click={handleAddButtonClicked}>褒め言葉を追加する</button>
  </div>
</main>
{#if showAddModal}
  <div class="overlay" on:click={() => (showAddModal = false)} />
  <div class="modal">
    <div class="modal-content" />
    <div class="input-container">
      <input
        bind:value={addingPraiseWord}
        placeholder="褒め言葉を入力してね"
        on:keypress={handleAddWordInputPress}
      />
      <button on:click={handleAddWordSubmit}>追加する！</button>
    </div>
  </div>
{/if}

<style>
  .main-container {
    display: flex;
    flex-direction: column;
    height: 100vh;
    align-items: center;
    justify-content: center;
  }

  .main-image-container {
    padding: 10px;
  }

  .input-container {
    padding: 10px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    height: 50px;
  }

  img {
    border-radius: 50%; /* 角丸半径を50%にする(=円形にする) */
    width: 180px; /* ※縦横を同値に */
    height: 180px; /* ※縦横を同値に */
  }

  .praised {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 10px;
  }

  .overlay {
    position: absolute; /* overlayの絶対位置を左上を起点にし幅と高さを100%にする */
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.6);
    backdrop-filter: blur(3px); /* 一番下のbody領域をぼやかす */
    z-index: 5; /* z-indexの数字を0と10の間の数字にし、bodyとmodalの真ん中に位置させる */
  }

  .modal {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: white;
    border-radius: 5px;
    box-shadow: 0 10px 10px rgba(0, 0, 0, 0.3); /* modalに影を作る */
    z-index: 10; /* z-indexでmodalを一番上に位置させる */
    width: 30%;
    padding: 10px;
  }

  @media (min-width: 640px) {
    main {
      max-width: none;
    }
  }
</style>
