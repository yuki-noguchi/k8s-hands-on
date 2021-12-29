<script lang="ts">
  const url = "http://api.michiko.praise.com/praise/";
  let mihikoImg = "images/michiko.jpg";
  let name = "";
  let praised = "";

  const postPraise = async () => {
    if (!name) {
      return;
    }
    const res = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name: name }),
    });
    praised = await res.text();
  };

  const handleKeyPress = async (e: KeyboardEvent) => {
    if (e.key === "Enter") {
      await postPraise();
    }
  };

  const handleSubmit = async () => {
    await postPraise();
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
        on:keypress={handleKeyPress}
      />
      <button on:click={handleSubmit}>褒められる！</button>
    </div>
    <div class="praised">
      {#if praised}
        <p>こんなふうに褒めてくれたよ！</p>
        <p>{praised}</p>
      {/if}
    </div>
  </div>
</main>

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

  @media (min-width: 640px) {
    main {
      max-width: none;
    }
  }
</style>
