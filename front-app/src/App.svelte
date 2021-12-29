<script lang="ts">
  let mihikoImg = "images/michiko.jpg";
  let name: string = "";

  const handleKeyDown = (event: CustomEvent | KeyboardEvent) => {
    event = event as KeyboardEvent;
    if (event.key === "Enter") {
      // post
    }
  };

  const handleSubmit = () => {
    // post
  };
</script>

<main>
  <div class="main-container">
    <div class="main-image-container">
      <img src={mihikoImg} alt="みちこさま" />
    </div>
    <div class="input-container">
      <input
        bind:value={name}
        placeholder="名前を入力してね"
        onkeydown={handleKeyDown}
      />
      <button on:click={handleSubmit}>褒められる！</button>
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
    padding-bottom: 20px;
  }

  img {
    border-radius: 50%; /* 角丸半径を50%にする(=円形にする) */
    width: 180px; /* ※縦横を同値に */
    height: 180px; /* ※縦横を同値に */
  }

  @media (min-width: 640px) {
    main {
      max-width: none;
    }
  }
</style>
